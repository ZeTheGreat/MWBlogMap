import DAO.MySQL.ComentarioDAOMySQL;
import Model.Comentario;
import Model.Post;
import Model.Usuario;
import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class comentariotest {

    Comentario com;
    ComentarioDAOMySQL comdao;
    Usuario usu;
    Post post;

    @Before
    public void setup() throws SQLException {
        com= new Comentario();
        post = new Post();
        comdao = new ComentarioDAOMySQL();
        usu = new Usuario();
    }

    @Test
    public void inserircomentario(){
        comdao.criarComentario(1L,1L,"texto");
    }



    @Test
    public void acharUsuarioDoComentario(){
        System.out.println(comdao.acharUsuarioDoComentario(1L).toString());
    }

    @Test
    public void acharcomentariosporpost(){
        System.out.println(comdao.acharComentariosPorPost(5L).get(0).getTexto());
    }
    @Test
    public void alterarComentario(){
        comdao.alterarComentario(1L,"TEXTO NOVO");
    }
    @Test
    public void deletarComentario(){
        comdao.deletarComentario(1L);
    }
    @Test
    public void deletarcomentarioporusu(){
        comdao.deletarComentarioPorUsuario(1L);
    }
    @Test
    public void deletarPorPost(){
        post.setTexto("texto");
        post.setImg("img");
        post.setTitulo("titulo");
        post.setId(5L);
        comdao.deletarComentarioPorPost(post);
    }
}
