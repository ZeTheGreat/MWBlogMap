package DAO.MySQL;

import DAO.Interface.SubComentarioDAO;
import DAO.Interface.UsuarioDAO;
import Model.Comentario;
import Model.SubComentario;
import Model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class SubComentarioDAOMySQL implements SubComentarioDAO {

    private ConexaoMySQL conect ;
    private String table="create table if not exists Subcomentario(\n" +
            "id_subcom int auto_increment,\n" +
            "txt_subcom varchar(5000),\n" +
            "id_com int,\n" +
            "id_usu int,\n" +
            "constraint id_comSC foreign key (id_com) references Comentario (id_com),\n" +
            "constraint id_usuSC foreign key (id_usu) references Usuario (id_usu),\n" +
            "constraint pk primary key(id_subcom,id_com)\n" +
            ");";
    private Connection conexao;

    public SubComentarioDAOMySQL() throws SQLException {
        conect = new ConexaoMySQL();
        conexao= conect.getConection();
        conexao.createStatement().execute(table);
    }

    @Override
    public void apagarComentariosFilhos(Comentario comentario) {
        try{
            PreparedStatement excluir = conexao.prepareStatement("delete from subcomentario where id_com = ?");
            excluir.setLong(1,comentario.getId());
            excluir.executeUpdate();
            excluir.close();
            System.out.println("Sub comentarios apagados com sucesso");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao apagar comentario");
        }
    }

    @Override
    public ArrayList<SubComentario> buscarComentariosFilhosPorComentarioPai(Comentario comentario) {
        try{
            SubComentario subcomaux = new SubComentario();
            ArrayList<SubComentario> comentariosfilhos = new ArrayList();
            PreparedStatement busca = conexao.prepareStatement("select * from subcomentario where id_com =?");
            busca.setLong(1,comentario.getId());
            ResultSet rs = busca.executeQuery();
            while (rs.next()){
                    subcomaux.setId(rs.getLong("id_subcom"));
                    subcomaux.setIdcomentario(rs.getLong("id_com"));
                    subcomaux.setIdusuario(rs.getLong("id_usu"));
                    subcomaux.setTexto(rs.getString("txt_subcom"));
                    comentariosfilhos.add(subcomaux);
            }
            busca.close();
            rs.close();
            System.out.println("comentarios filhos encontrados via pai");
            return comentariosfilhos;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao encontrar comentarios filhos via pai");
        }
        return null;
    }

    @Override
    public void alterarSubComentario(SubComentario velho, SubComentario novo) {
        try{
            excluirSubComentario(velho);
            adicionarSubComentario(novo);
            System.out.println("alteracao do subcomentario feita coms sucesso");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao alterar subcomentario");
        }
    }

    @Override
    public void excluirSubComentario(SubComentario subcomentario) {
        try{
            PreparedStatement excluir = conexao.prepareStatement("delete from subcomentario where id_subcom=?");
            excluir.setLong(1,subcomentario.getId());
            excluir.executeUpdate();
            System.out.println("subcomentario excluido");
            excluir.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("problema ao excluir subcomentario");
        }
    }

    @Override
    public void adicionarSubComentario(SubComentario subcomentario) {
        try{
            PreparedStatement inserir = conexao.prepareStatement("insert into subcomentario (txt_subcom, id_com, " +
                    "id_usu) values (?,?,?)");
            inserir.setString(1,subcomentario.getTexto());
            inserir.setLong(2,subcomentario.getIdcomentario());
            inserir.setLong(3,subcomentario.getIdusuario());
            inserir.executeUpdate();
            inserir.close();
            System.out.println("sub comentario inserido");
            System.out.println("subcomentario inserido");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("problema ao adicionar subcomentario");
        }
    }

    @Override
    public Usuario buscarUsuDoSubComentario(SubComentario subcomentario) {
        try{
            Usuario usu= new Usuario();
            UsuarioDAO usuaux = new UsuarioDAOMySQL();
            PreparedStatement busca = conexao.prepareStatement("select id_usu from subcomentario where id_subcom = ?");
            busca.setLong(1,subcomentario.getId());
            ResultSet rs = busca.executeQuery();
            while (rs.next()){
                usu=usuaux.buscarUsuarioPorId(rs.getLong("id_usu"));
            }
            rs.close();
            busca.close();
            return usu;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha ao buscar usuario do subcomentario");
        }
        return null;
    }
}
