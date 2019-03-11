package DAO.MySQL;

import DAO.Interface.PostUsuDAO;
import Model.Post;
import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostUsuDAOMySQL implements PostUsuDAO {
    private ConexaoMySQL conect ;
    private String table="create table if not exists PostUsu(\n" +
            "id_post int,\n" +
            "id_usu int,\n" +
            "constraint id_postPU foreign key(id_post) references Post (id_post),\n" +
            "constraint id_usuPU foreign key(id_usu) references Usuario (id_usu),\n" +
            "constraint pk primary key(id_post,id_usu) \n" +
            ");";
    private Connection conexao;

    public PostUsuDAOMySQL() throws SQLException {
        conect = new ConexaoMySQL();
        conexao= conect.getConection();
        conexao.createStatement().execute(table);
    }

    @Override
    public ArrayList<Post> procurarPostPorUsuario(Usuario u) {
        try{
            ArrayList<Post> posts = new ArrayList<>();
            PostDAOMySQL postDAO = new PostDAOMySQL();
            PreparedStatement busca = conexao.prepareStatement("select id_post from postusu where id_usu=?");
            busca.setLong(1,u.getId());
            ResultSet rs = busca.executeQuery();
            while (rs.next()){
                posts.add(postDAO.buscarPorID(rs.getLong("id_post")));
            }
            System.out.println("Posts procurados via usu");
            busca.close();
            rs.close();
            return posts;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro em procurar post por usu");
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> procurarUsuariosporPost(Post p) {
        try{
            ArrayList<Usuario> usus = new ArrayList<>();
            UsuarioDAOMySQL usuDAO = new UsuarioDAOMySQL();
            PreparedStatement busca = conexao.prepareStatement("select id_usu from postusu where id_post=?");
            busca.setLong(1,p.getId());
            ResultSet rs = busca.executeQuery();
            while (rs.next()){
                usus.add(usuDAO.buscarUsuarioPorId(rs.getLong("id_usu")));
            }
            System.out.println("Usuarios procurados via post");
            busca.close();
            rs.close();
            return usus;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro em procurar post por usu");
        }
        return null;
    }

    @Override
    public void alterarUsuariosdoPost(ArrayList<Usuario> usus, Post p) {
        try{
           excluirpost(p);
           inserirListUsuariosPorPost(usus, p);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha ao alterar Usuarios do Post");
        }
    }

    @Override
    public void inserirListUsuariosPorPost(ArrayList<Usuario> usus, Post post) {
        try{
            PreparedStatement inserir = conexao.prepareStatement("insert into postusu (id_post, id_usu)" +
                    "values (?,?)");
            for(Usuario usu : usus){
                inserir.setLong(1,post.getId());
                inserir.setLong(2,usu.getId());
                inserir.executeUpdate();
            }
            inserir.close();
            System.out.println("Usuarios inseridos via post");

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao inserir usuarios por post");
        }
    }

    @Override
    public void excluirPostPorUsuario(Usuario usu) {
        try {
            PostDAOMySQL postDAO = new PostDAOMySQL();
            ArrayList<Post> posts=procurarPostPorUsuario(usu);
            PreparedStatement excluir = conexao.prepareStatement("delete from postusu where id_usu =?");
            excluir.setLong(1,usu.getId());
            excluir.executeUpdate();
            for(Post post :posts){
                postDAO.excluirPost(post.getId());
            }
            System.out.println("Post excluido via usuario");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro ao excluir Post por usuario");
        }
    }

    @Override
    public void excluircertoPostPorUsuario(Usuario usu, Post p) {
        try {

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao excluir certo post via usuario");
        }
    }

    @Override
    public void excluirpost(Post p) {
        try {
            PreparedStatement excluir = conexao.prepareStatement("delete from postusu where id_post=?");
            excluir.setLong(1,p.getId());
            excluir.executeUpdate();
            System.out.println("post excluido com sucesso");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro ao excluir post");
        }

    }
}
