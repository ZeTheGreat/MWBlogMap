package DAO.Interface;

import Model.Papel;
import Model.Usuario;

import java.util.ArrayList;

public interface UsuarioPapelDAO {
    void inserirPapelUsuario(long id, ArrayList<Papel> papeis);
    ArrayList<Usuario>buscarUsuariosporPapel(Papel p);
    ArrayList<Papel>buscarPapeisPorUsuario(Usuario u);
    void alterarPapeisdoUsuario(Usuario u);
    void excluirPapeisdoUsuario(Usuario u);
}
