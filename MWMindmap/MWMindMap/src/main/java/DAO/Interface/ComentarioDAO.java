package DAO.Interface;

import Model.Comentario;
import Model.Post;
import Model.Usuario;

import java.util.ArrayList;

public interface ComentarioDAO {
    Usuario acharUsuarioDoComentario(long idcom);
    ArrayList<Comentario> acharComentariosPorPost(long idpost);
    Comentario acharComentarioporID(long idcomentario);
    void alterarComentario(long idcomentario,String novocomentario);
    void deletarComentario(long idcomentario);
    void criarComentario(long idpost,long idusu,String comentario);
    void deletarComentarioPorUsuario(long idusu);
    Comentario acharComentarioPorUsuario(long idusu);
    void deletarComentarioPorPost(Post post);
}
