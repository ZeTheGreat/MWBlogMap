package DAO.MySQL;

import Model.*;


import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        UsuarioDAOMySQL test;
        CategoriaDAOMySQL cattest = new CategoriaDAOMySQL();
        test = new UsuarioDAOMySQL();
        ArrayList papeis = new ArrayList<Papel>();
        Papel papel= new Papel(1,PapelEnum.ADM);
        Papel papel2=new Papel(2,PapelEnum.USU);
        papeis.add(papel);
        papeis.add(papel2);
        Usuario usu= new Usuario(1,"José2","1234","123","Jose@jose.com",papeis);
        papeis.remove(papel2);
        Usuario usu2= new Usuario(1,"José3","1234","123","Jose@jose.com",papeis);

        if(test.ValidarUsuario(usu.getLogin()))
            test.InserirUsuario(usu);
        /*
        ArrayList<Usuario> usus=test.listarUsuarios();
        for(Usuario susu :usus){
            System.out.println(susu.getNome());
            System.out.println(susu.getPapeis());
        }
        test.AlterarUsuario(usu, usu2);
        PapelDAOMySQL testao = new PapelDAOMySQL();
        System.out.println(testao.Buscartodos());
        Categoria cat1 = new Categoria();
        Categoria cat2 = new Categoria();
        Categoria cat3 = new Categoria();
        cat1.setDescricao("estudo");
        cat2.setDescricao("viver");
        cat3.setDescricao("teste");
        ArrayList<Categoria> cats = new ArrayList<>();
        cats.add(cat1);
        cat1.setId(1);
        cats.add(cat2);
        cattest.inserirCategoria(cat1);
        cattest.inserirCategoria(cat2);
        cattest.inserirCategoria(cat3);
        PostDAOMySQL postDAO = new PostDAOMySQL();
        ArrayList<Usuario> ususs = new ArrayList<>();
        for( Usuario usuuuu : ususs)
            test.InserirUsuario(usuuuu);
        Post post = new Post();
        post.setTitulo("Oiii");
        post.setTexto("Oi oi oi oi ");
        post.setImg("imgimgimg");
        cats.add(cat3);
        post.setId(1);
        post.setTexto("textexto");
        postDAO.adicionarPost(post,usus,cats);
        Post popost = postDAO.buscarPorID(cat1.getId());
        postDAO.alterarPost(post.getId(),post,ususs,cats);
        postDAO.excluirPost(post.getId());

        ArrayList<Post> postsss = postDAO.buscarTodos();/*
        for (Post postizim : postsss)
            System.out.println(postizim.getTitulo());
        postDAO.alterarPost(post.getId(),post,ususs,cats);
        postDAO.excluirPost(post.getId());


/*
        cattest.inserirCategoria(cat1);
        System.out.println(cattest.buscarPorNome("estudo").getDescricao());
        cattest.alterarCategoria(cat1,cat2);
        System.out.println(cattest.buscarPorID(2).getDescricao());
        cattest.excluirCategoria(cat2);
        //cattest.inserirCategoria(cat1);
        //cattest.inserirCategoria(cat3);
        //System.out.println(cattest.bucarTodas());




        ComentarioDAOMySQL comentest = new ComentarioDAOMySQL();
        Comentario com1= new Comentario();
        Comentario com2= new Comentario();
        Comentario com3=new Comentario();
        com1.setTexto("hssikjhd");
        com1.setIdusuario(1);
        com1.setIdpost(1);
        com1.setId(1);
        com1.setTexto("hssikjhd");
        com1.setIdusuario(1);
        com1.setIdpost(1);
        com1.setId(1);
        com1.setTexto("hssikjhd");
        com1.setIdusuario(1);
        com1.setIdpost(1);
        com1.setId(1);*/
    }
}