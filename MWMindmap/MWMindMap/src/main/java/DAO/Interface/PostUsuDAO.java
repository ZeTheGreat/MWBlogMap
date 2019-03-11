package DAO.Interface;

import Model.Post;
import Model.Usuario;

import java.util.ArrayList;

public interface PostUsuDAO {
    ArrayList<Post> procurarPostPorUsuario(Usuario u);
    ArrayList<Usuario> procurarUsuariosporPost(Post p);
    void alterarUsuariosdoPost(ArrayList<Usuario> usus, Post p);
    void inserirListUsuariosPorPost(ArrayList<Usuario> usus, Post post);
    void excluirPostPorUsuario(Usuario usu);
    void excluircertoPostPorUsuario(Usuario usu, Post p);
    void excluirpost(Post p);

}
