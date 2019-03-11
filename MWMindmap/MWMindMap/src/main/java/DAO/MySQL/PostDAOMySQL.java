package DAO.MySQL;

import DAO.Interface.ComentarioDAO;
import DAO.Interface.PostDAO;
import Model.Categoria;
import Model.Comentario;
import Model.Post;
import Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PostDAOMySQL implements PostDAO {

    private ConexaoMySQL conect ;
    private String table="create table if not exists Post(\n" +
            "id_post int auto_increment primary key,\n" +
            "titulo_post varchar(20),\n" +
            "categoria_post varchar(20),\n" +
            "texto_post varchar(5000),\n" +
            "img_post varchar(100)\n" +
            ");";
    private Connection conexao;

    public PostDAOMySQL() throws SQLException {
        conect = new ConexaoMySQL();
        conexao= conect.getConection();
        conexao.createStatement().execute(table);
    }

    private Post contrutorPost(ResultSet rs) throws SQLException {
        Post postaux= new Post();
        postaux.setId(rs.getLong("id_post"));
        postaux.setImg(rs.getString("img_post"));
        postaux.setTexto(rs.getString("texto_post"));
        postaux.setTitulo(rs.getString("titulo_post"));
        return postaux;
    }

    public void excluirPostPorUsuario(Usuario u){

    }

    @Override
    public void adicionarPost(Post post, ArrayList<Usuario> usus, ArrayList<Categoria> cats) {
        try {
            PostUsuDAOMySQL postusuDAO = new PostUsuDAOMySQL();
            PostCategoriaDAOMySQL postcatDAO = new PostCategoriaDAOMySQL();
            PreparedStatement inserir= conexao.prepareStatement("insert into post(titulo_post,texto_post" +
                    ", img_post) values (?,?,?)");
            inserir.setString(1,post.getTitulo());
            inserir.setString(2,post.getTexto());
            inserir.setString(3,post.getImg());
            inserir.executeUpdate();
           // postcatDAO.inserirCategoriadoPost(post,cats);
           // postusuDAO.inserirListUsuariosPorPost(usus,post);
            inserir.close();
            System.out.println("post adicionado");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha ao adicionar post");
        }
    }
    public void adicionarPost(Post post) {
        try {
            PreparedStatement inserir= conexao.prepareStatement("insert into post(titulo_post,texto_post" +
                    ") values (?,?)");
            inserir.setString(1,post.getTitulo());
            inserir.setString(2,post.getTexto());
            inserir.executeUpdate();
            inserir.close();
            System.out.println("post adicionado");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha ao adicionar post");
        }
    }

    @Override
    public void excluirPost(long idpost) {
        try {
            PostUsuDAOMySQL postusuDAO = new PostUsuDAOMySQL();
           // PostCategoriaDAOMySQL postcatDAO = new PostCategoriaDAOMySQL();
            ComentarioDAOMySQL comentDAO = new ComentarioDAOMySQL();
            PostDAOMySQL postDAO = new PostDAOMySQL();
            comentDAO.deletarComentarioPorPost(postDAO.buscarPorID(idpost));
            postusuDAO.excluirpost(buscarPorID(idpost));
           // postcatDAO.excluirRelacaoPostCat(buscarPorID(idpost));
            PreparedStatement excluir= conexao.prepareStatement("delete from post where id_post=?");
            excluir.setLong(1,idpost);
            excluir.executeUpdate();
            System.out.println("post excluido");
            excluir.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha ao excluir post");
        }
    }

    @Override
    public void alterarPost(long idpost, Post post,ArrayList<Usuario> usus, ArrayList<Categoria> cats) {
        try {
            excluirPost(idpost);
            adicionarPost(post,usus,cats);
            System.out.println("Post Alterado");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha ao alterar post");
        }
    }

    @Override
    public ArrayList<Post> bucarPorNome(String nomepost) {
        try {
            ArrayList<Post> posts = new ArrayList<>();
            PreparedStatement busca = conexao.prepareStatement("select * from post where titulo_post =?");
            busca.setString(1,nomepost);
            ResultSet rs=busca.executeQuery();
            while (rs.next()){
                posts.add(contrutorPost(rs));
            }
            System.out.println("Post achado por nome");
            rs.close();
            busca.close();
            return posts;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha ao buscar post por nome");
        }
        return null;
    }

    @Override
    public Post buscarPorID(Long id) {
        try {
            Post postaux = null;
            PreparedStatement busca = conexao.prepareStatement("select * from post where id_post =?");
            busca.setLong(1,id);
            ResultSet rs=busca.executeQuery();
            while (rs.next()){
                postaux=contrutorPost(rs);
            }
            System.out.println("Post achado por nome");
            rs.close();
            busca.close();
            return postaux;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha ao buscar post por nome");
        }
        return null;
    }

    @Override
    public ArrayList<Post> buscarTodos() {
        try {
            ArrayList<Post> posts = new ArrayList<>();
            PreparedStatement busca = conexao.prepareStatement("select * from post");
            ResultSet rs=busca.executeQuery();
            while (rs.next()){
                posts.add(contrutorPost(rs));
            }
            rs.close();
            busca.close();
            System.out.println("Posts buscados");
            return posts;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha ao buscar todos post");
        }
        return null;
    }

    @Override
    public ArrayList<Post> buscarPorUsuario(Usuario usu) {
        return null;
    }
}
