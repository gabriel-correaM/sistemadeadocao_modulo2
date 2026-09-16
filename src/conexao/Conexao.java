package conexao;

import config.Database;
import java.sql.Connection;
import java.sql.SQLException;


public final class Conexao {

    private Conexao() {
    }

    public static Connection conectar() {
        try {
            return Database.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco de dados.", e);
        }
    }

    public static void criarTabelas() {
        Database.initialize();
    }
}
