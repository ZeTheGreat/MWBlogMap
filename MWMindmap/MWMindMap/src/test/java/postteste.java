import DAO.MySQL.PostDAOMySQL;
import Model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.sql.SQLException;
import java.util.ArrayList;

public class postteste  {
    PostDAOMySQL postdao;
    Post post,post2;
    ArrayList<Usuario> usus;
    ArrayList<Categoria> cats;
    ArrayList<Papel> papeis;

    @Before
    public void setup() throws SQLException {
        postdao = new PostDAOMySQL();
        post = new Post();
        papeis = new ArrayList<>();
        usus= new ArrayList<>();
        cats= new ArrayList<>();
        post2=new Post();
    }

    @Test
    public void testfinal(){

    }

    @Test
    public void inserirpost(){
        Papel papel= new Papel(1, PapelEnum.ADM);
        Papel papel2=new Papel(2,PapelEnum.USU);
        papeis.add(papel);
        papeis.add(papel2);
        Usuario usu= new Usuario(1,"José2","1234","123","Jose@jose.com",papeis);
        Usuario usu2= new Usuario(1,"José3","1234","123","Jose@jose.com",papeis);
        usus.add(usu);
        usus.add(usu2);
        post.setTexto("texto");
        post.setImg("img");
        post.setTitulo("titulo");
        postdao.adicionarPost(post,usus,cats);
    }
    @Test
    public void excluirpost(){
        post=postdao.buscarPorID(1L);
        postdao.excluirPost(post.getId());
    }
    @Test
    public void alterarpost(){
        Papel papel= new Papel(1, PapelEnum.ADM);
        Papel papel2=new Papel(2,PapelEnum.USU);
        papeis.add(papel);
        papeis.add(papel2);
        Usuario usu= new Usuario(1,"José5","1234","123","Jose@jose.com",papeis);
        Usuario usu2= new Usuario(1,"José4","1234","123","Jose@jose.com",papeis);
        usus.add(usu);
        usus.add(usu2);
        post2.setTexto("texto");
        post2.setImg("img");
        post2.setTitulo("Titulo");
        post = postdao.buscarPorID(4L);
        postdao.alterarPost(post.getId(),post2,usus,cats);
    }
    @Test
    public void buscarpnome(){
        System.out.println(postdao.bucarPorNome("Titdfgdfgulo").get(0).getImg());
    }
    @Test
    public void buscapID(){
        System.out.println(postdao.buscarPorID(5l).getTexto());
    }
    @Test
    public void buscartodos(){
        System.out.println(postdao.buscarTodos().get(0).getId());
    }
}
