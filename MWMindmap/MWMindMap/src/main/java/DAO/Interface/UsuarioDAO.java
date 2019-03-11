package DAO.Interface;

import Model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UsuarioDAO {
    ArrayList<Usuario> buscarUsuarioPorNome(String nomeusu);
    Usuario buscarUsuarioPorId(long id);
    Usuario bucarUsuarioPorLogin(String login) throws SQLException;
    void InserirUsuario(Usuario usuario);
    ArrayList<Usuario> listarUsuarios();
    void ExcluirUsuario(Usuario usuario);
    void AlterarUsuario(Usuario velhousuario,Usuario novousario);
}
