package DAO.MySQL;

import DAO.Interface.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoMySQL implements Conexao {
    Connection conexao;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/" +
            "mwblog?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private String USERNAME = "root";
    private String PASSWORD = "";

    public ConexaoMySQL() throws SQLException {
        criarConexao();
    }

    @Override
    public void criarConexao() throws SQLException {
        pegarConexao();
    }

    @Override
    public Connection getConection() {
        return conexao;
    }

    public void pegarConexao(){
        try {
            Class.forName(DRIVER);

            conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Conectado com sucesso");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro de conexão... CategoriaDAO: ", e);
        }
    }


}
