package DAO.Interface;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public interface Conexao {
    void criarConexao() throws SQLException;
    Connection getConection();
}
