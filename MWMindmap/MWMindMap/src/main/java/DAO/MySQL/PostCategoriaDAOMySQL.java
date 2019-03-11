package DAO.MySQL;

import DAO.Interface.PostCategoriaDAO;
import DAO.Interface.PostDAO;
import Model.Categoria;
import Model.Post;
import com.mysql.cj.protocol.Resultset;
import javafx.geometry.Pos;

import java.sql.*;
import java.util.ArrayList;

public class PostCategoriaDAOMySQL implements PostCategoriaDAO {

    private ConexaoMySQL conect ;
    private String table="create table if not exists CategoriaPost(\n" +
            "id_post int,\n" +
            "id_cat int,\n" +
            "constraint id_postCP foreign key(id_post) references Post (id_post),\n" +
            "constraint id_catCP foreign key(id_cat) references Categoria (id_cat),\n" +
            "constraint pk primary key(id_post,id_cat)\n" +
            ");";
    private Connection conexao;

    public PostCategoriaDAOMySQL() throws SQLException {
        conect = new ConexaoMySQL();
        conexao= conect.getConection();
        conexao.createStatement().execute(table);
    }

    @Override
    public void inserirCategoriadoPost(Post p, ArrayList<Categoria> cats) {
        try {
            for(Categoria cat : cats) {
                PreparedStatement inserir = conexao.prepareStatement("insert into categoriapost(id_post,id_cat) values (?,?)");
                inserir.setLong(1,p.getId());
                System.out.println(cat.getId());
                inserir.setLong(2, cat.getId());
                System.out.println(cat.getId());
                inserir.executeUpdate();
                inserir.close();
            }
            System.out.println("categoria inserida via post");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao inserir as categorias do post");
        }
    }

    @Override
    public void excluirRelacaoPostCat(Post p) {
        try {
            PreparedStatement excluir = conexao.prepareStatement("delete from categoriapost where id_post = ?");
            excluir.setLong(1,p.getId());
            excluir.executeUpdate();
            excluir.close();
            System.out.println("relacao post cat foi feita");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("problemas ao excluir relacao post cat");
        }
    }

    @Override
    public void excluirRelacaoCatPost(Categoria cat){
        try {
            PreparedStatement excluir = conexao.prepareStatement("delete from categoriapost where id_cat = ?");
            excluir.setLong(1,cat.getId());
            excluir.executeUpdate();
            excluir.close();
            System.out.println("relacao cat post foi feita");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("problemas ao excluir relacao cat post");
        }
    }

    @Override
    public void alterarCategoriasdoPost(Post p, ArrayList<Categoria> cats) {
        try {
            excluirRelacaoPostCat(p);
            inserirCategoriadoPost(p,cats);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao alterar a categoria do post");
        }
    }

    @Override
    public ArrayList<Post> bucasPostsPorCategoria(Categoria cat)
    {
        try {
            ArrayList<Post> posts = new ArrayList<>();
            PostDAOMySQL post = new PostDAOMySQL();
            PreparedStatement busca = conexao.prepareStatement("select id_post from categoriapost where id_cat =?");
            busca.setLong(1,cat.getId());
            ResultSet rs = busca.executeQuery();
            while (rs.next()){
                posts.add(post.buscarPorID(rs.getLong("id_post")));
            }
            System.out.println("posts buscados por categoria");
            return posts;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao buscar post por categoria");
    }
        return null;
    }

    @Override
    public ArrayList<Categoria> buscarCategoriaPorPost(Post post) {

        try {
            ArrayList<Categoria> cats = new ArrayList<>();
            CategoriaDAOMySQL cat = new CategoriaDAOMySQL();
            PreparedStatement busca = conexao.prepareStatement("select id_cat from categoriapost where id_post = ?");
            busca.setLong(1,post.getId());
            ResultSet rs = busca.executeQuery();
            while (rs.next()){
                cats.add(cat.buscarPorID(rs.getLong("id_cat")));
            }
            System.out.println("falha ao buscar categoria post");
            return cats;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(" falha ao buscar categoria por post");
        }return null;
    }
}
