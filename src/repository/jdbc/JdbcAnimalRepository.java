package repository.jdbc;

import config.Database;
import consulta.Animais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import repository.AnimalRepository;

public class JdbcAnimalRepository implements AnimalRepository {

    @Override
    public int salvar(Animais animal) {

        String sql =
                "INSERT INTO animais "
                + "(nome, raca, abrigo, adotado, adotante_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, animal.getNome());
            statement.setString(2, animal.getRaca());
            statement.setString(3, animal.getAbrigo());
            statement.setString(4, animal.getAdotado());

            if (animal.getAdotante_id() > 0) {
                statement.setInt(5, animal.getAdotante_id());
            } else {
                statement.setNull(5, Types.INTEGER);
            }

            statement.executeUpdate();

            try (ResultSet result = statement.getGeneratedKeys()) {
                if (result.next()) {
                    int id = result.getInt(1);
                    animal.setId(id);
                    return id;
                }
            }

            throw new SQLException("O banco não retornou o ID do animal.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar animal.", e);
        }
    }

    @Override
    public List<Animais> listar() {

        String sql =
                "SELECT id, nome, raca, abrigo, adotado, adotante_id "
                + "FROM animais ORDER BY id";

        List<Animais> animais = new ArrayList<>();

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Animais animal = new Animais();

                animal.setId(result.getInt("id"));
                animal.setNome(result.getString("nome"));
                animal.setRaca(result.getString("raca"));
                animal.setAbrigo(result.getString("abrigo"));
                animal.setAdotado(result.getString("adotado"));
                animal.setAdotante_id(result.getInt("adotante_id"));

                animais.add(animal);
            }

            return animais;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar animais.", e);
        }
    }

    @Override
    public void excluir(int id) {

        String sql = "DELETE FROM animais WHERE id = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir animal.", e);
        }
    }

    @Override
    public void atualizarAdocao(Animais animal) {

        String sql =
                "UPDATE animais SET adotado = ?, adotante_id = ? "
                + "WHERE id = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, animal.getAdotado());

            if (animal.getAdotante_id() > 0) {
                statement.setInt(2, animal.getAdotante_id());
            } else {
                statement.setNull(2, Types.INTEGER);
            }

            statement.setInt(3, animal.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar adoção.", e);
        }
    }
}
