package repository.jdbc;

import config.Database;
import consulta.Adotantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import repository.AdotanteRepository;

public class JdbcAdotanteRepository implements AdotanteRepository {

    @Override
    public int salvar(Adotantes adotante) {

        String sql =
                "INSERT INTO adotantes (nome, senha, telefone, email) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, adotante.getNome());
            statement.setString(2, adotante.getSenha());
            statement.setString(3, adotante.getTelefone());
            statement.setString(4, adotante.getEmail());
            statement.executeUpdate();

            try (ResultSet result = statement.getGeneratedKeys()) {
                if (result.next()) {
                    int id = result.getInt(1);
                    adotante.setId(id);
                    return id;
                }
            }

            throw new SQLException("O banco não retornou o ID do adotante.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar adotante.", e);
        }
    }

    @Override
    public Optional<Adotantes> buscarPorId(int id) {

        String sql =
                "SELECT id, nome, senha, telefone, email "
                + "FROM adotantes WHERE id = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {

                    Adotantes adotante = new Adotantes();

                    adotante.setId(result.getInt("id"));
                    adotante.setNome(result.getString("nome"));
                    adotante.setSenha(result.getString("senha"));
                    adotante.setTelefone(result.getString("telefone"));
                    adotante.setEmail(result.getString("email"));

                    return Optional.of(adotante);
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar adotante.", e);
        }
    }
}
