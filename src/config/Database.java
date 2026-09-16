package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sistemadeadocao?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initialize() {

        String sqlAdotantes =
                "CREATE TABLE IF NOT EXISTS adotantes ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nome VARCHAR(50) NOT NULL, "
                + "senha VARCHAR(100) NOT NULL, "
                + "telefone VARCHAR(100) NOT NULL, "
                + "email VARCHAR(100) NOT NULL"
                + ")";

        String sqlAnimais =
                "CREATE TABLE IF NOT EXISTS animais ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nome VARCHAR(100) NOT NULL, "
                + "raca VARCHAR(20) NOT NULL, "
                + "abrigo VARCHAR(50) NOT NULL, "
                + "adotado VARCHAR(20) NOT NULL, "
                + "adotante_id INT, "
                + "FOREIGN KEY (adotante_id) REFERENCES adotantes(id)"
                + ")";

        try (Connection connection = getConnection();
                Statement statement = connection.createStatement()) {

            statement.executeUpdate(sqlAdotantes);
            statement.executeUpdate(sqlAnimais);

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Não foi possível inicializar o banco de dados.", e);
        }
    }
}
