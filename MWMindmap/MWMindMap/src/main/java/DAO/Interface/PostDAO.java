package DAO.Interface;

import Model.Categoria;
import Model.Post;
import Model.Usuario;

import java.util.ArrayList;

public interface PostDAO {
    void adicionarPost(Post post, ArrayList<Usuario> usus, ArrayList<Categoria> cats);
    void excluirPost(long idpost);
    void alterarPost(long idpost, Post post,ArrayList<Usuario> usus, ArrayList<Categoria> cats);
    ArrayList<Post> bucarPorNome(String nomepost);
    Post buscarPorID(Long id);
    ArrayList<Post> buscarTodos();
    ArrayList<Post> buscarPorUsuario(Usuario usu);
}
