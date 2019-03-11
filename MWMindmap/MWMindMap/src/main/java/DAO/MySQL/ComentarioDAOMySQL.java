package DAO.MySQL;

import DAO.Interface.ComentarioDAO;
import Model.Comentario;
import Model.Post;
import Model.Usuario;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;

public class ComentarioDAOMySQL implements ComentarioDAO {

    private ConexaoMySQL conect ;
    private String table="create table if not exists Comentario(\n" +
            "id_com int auto_increment primary key,\n" +
            "txt_com varchar(5000),\n" +
            "id_usu int,\n" +
            "id_post int,\n" +
            "constraint id_usuC foreign key (id_usu) references Usuario (id_usu),\n" +
            "constraint id_postC foreign key(id_post) references Post (id_post)\n" +
            ");";
    private Connection conexao;

    public ComentarioDAOMySQL() throws SQLException {
        conect = new ConexaoMySQL();
        conexao= conect.getConection();
        conexao.createStatement().execute(table);
    }

    private Comentario comentarioConstruidor(ResultSet rs) throws SQLException {
        Comentario comentario =new Comentario();
        comentario.setId(rs.getLong("id_com"));
        comentario.setIdpost(rs.getLong("id_post"));
        comentario.setIdusuario(rs.getLong("id_usu"));
        comentario.setTexto(rs.getString("txt_com"));
        return comentario;
    }

    @Override
    public Usuario acharUsuarioDoComentario(long idcom) {
        try{
            Usuario usuaux = null;
            PreparedStatement busca = conexao.prepareStatement("select id_usu from comentario where id_com=?");
            busca.setLong(1,idcom);
            ResultSet rs=busca.executeQuery();
            while (rs.next()){
                UsuarioDAOMySQL usuDAO = new UsuarioDAOMySQL();
                usuaux=usuDAO.buscarUsuarioPorId(rs.getLong("id_usu"));
            }
            busca.close();
            rs.close();
            System.out.println("Usuario achado");
            return usuaux;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro ao achar usuario do comentario");
        }

        return null;

    }

    @Override
    public ArrayList<Comentario> acharComentariosPorPost(long idpost) {
        try {
            ArrayList<Comentario> comentarios = new ArrayList<>();
            PreparedStatement busca = conexao.prepareStatement("select * from comentario where id_post=?");
            busca.setLong(1,idpost);
            ResultSet rs = busca.executeQuery();
            while (rs.next()){
                comentarios.add(comentarioConstruidor(rs));
            }
            System.out.println("Comentario achado via post");
            rs.close();
            busca.close();
            return comentarios;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao achar comentarios por post");
        }
        return null;
    }

    @Override
    public Comentario acharComentarioporID(long idcomentario) {
        try {
            Comentario comentaux=null;
            PreparedStatement busca = conexao.prepareStatement("select * from comentario where id_com=?");
            busca.setLong(1,idcomentario);
            ResultSet rs = busca.executeQuery();
            while (rs.next()){
                comentaux=comentarioConstruidor(rs);
            }
            rs.close();
            busca.close();
            System.out.println("Comentarioa chado por ID");
            return comentaux;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao achar comentario por id");
        }
        return null;
    }

    @Override
    public void alterarComentario(long idcomentario, String novocomentario) {
        try {
            PreparedStatement update = conexao.prepareStatement("update comentario set txt_com =? where id_com=?");
            update.setString(1,novocomentario);
            update.setLong(2,idcomentario);
            update.executeUpdate();
            System.out.println("Comentario alterado");
            update.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao achar comentarios por post");
        }

    }

    @Override
    public void deletarComentario(long idcomentario) {
        try {
            SubComentarioDAOMySQL subcomentariDAO = new SubComentarioDAOMySQL();
            subcomentariDAO.apagarComentariosFilhos(acharComentarioporID(idcomentario));
            PreparedStatement excluir = conexao.prepareStatement("delete from comentario where id_com =?");
            excluir.setLong(1,idcomentario);
            excluir.executeUpdate();
            excluir.close();
            System.out.println("Comentario excluido");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao achar comentarios por post");
        }

    }

    @Override
    public void criarComentario(long idpost, long idusu, String comentario) {
        try {
            PreparedStatement inserir = conexao.prepareStatement("insert into comentario (txt_com,id_usu,id_post)" +
                    "values (?,?,?)");
            inserir.setString(1,comentario);
            inserir.setLong(2,idusu);
            inserir.setLong(3,idpost);
            inserir.executeUpdate();
            System.out.println("Comentario inserido");
            inserir.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao achar comentarios por post");
        }

    }
    @Override
    public Comentario acharComentarioPorUsuario(long idusu){
        try{
            PreparedStatement buscar = conexao.prepareStatement("select * from comentario where id_usu=?");
            buscar.setLong(1,idusu);
            ResultSet rs= buscar.executeQuery();
            while (rs.next()){
                Comentario com = new Comentario();
                com.setTexto(rs.getString("txt_com"));
                com.setIdusuario(rs.getLong("id_usu"));
                com.setIdpost(rs.getLong("id_post"));
                com.setId(rs.getLong("id_com"));
                buscar.close();
                rs.close();
                return  com;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao achar comentario por usuario");
        }
        return null;
    }


    @Override
    public void deletarComentarioPorUsuario(long idusu) {
        try {
            Comentario comentario=acharComentarioPorUsuario(idusu);
            SubComentarioDAOMySQL subcomenDAO = new SubComentarioDAOMySQL();
            subcomenDAO.apagarComentariosFilhos(comentario);
            PreparedStatement excluir = conexao.prepareStatement("delete from comentario where id_com =?");
            excluir.setLong(1,comentario.getId());
            excluir.executeUpdate();
            System.out.println("Comentario excluido com sucesso");
            excluir.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao achar comentarios por post");
        }

    }

    @Override
    public void deletarComentarioPorPost(Post post) {
        try{
            PreparedStatement busca = conexao.prepareStatement("select id_com from comentario where id_post = ? ");
            busca.setLong(1,post.getId());
            ResultSet rs = busca.executeQuery();
            while (rs.next()) {
                deletarComentario(rs.getLong("id_com"));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao deletar comentario por post");
        }
    }
}
