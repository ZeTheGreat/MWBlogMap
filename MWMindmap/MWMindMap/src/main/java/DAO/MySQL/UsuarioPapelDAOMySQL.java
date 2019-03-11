package DAO.MySQL;

import DAO.Interface.UsuarioPapelDAO;
import Model.Papel;
import Model.PapelEnum;
import Model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioPapelDAOMySQL implements UsuarioPapelDAO {

    private ConexaoMySQL conect ;
    private String table="create table if not exists PapelUsu(\n" +
            "id_pap int,\n" +
            "id_usu int,\n" +
            "constraint id_papPaU foreign key(id_pap) references Papel (id_pap),\n" +
            "constraint id_usuPaU foreign key(id_usu) references Usuario (id_usu),\n" +
            "constraint pk primary key(id_pap,id_usu) \n" +
            ");";
    private Connection conexao;

    public UsuarioPapelDAOMySQL() throws SQLException {
        conect = new ConexaoMySQL();
        conexao= conect.getConection();
        conexao.createStatement().execute(table);
    }

    @Override
    public void inserirPapelUsuario(long id, ArrayList<Papel> papeis) {
        try{
            PreparedStatement inserir = conexao.prepareStatement("insert into papelusu (id_pap, id_usu) VALUES (?,?)");
            for (Papel papel : papeis){
                inserir.setLong(2,id);
                System.out.println(papel.getIdpap());
                inserir.setLong(1,papel.getIdpap());
                inserir.executeUpdate();
            }
            inserir.close();
            System.out.println("Inserio papeis do usuario");

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Problema com inserir os papeis do usuario");
        }
    }

    @Override
    public ArrayList<Usuario> buscarUsuariosporPapel(Papel p) {
        try{
            UsuarioDAOMySQL usuDAO = new UsuarioDAOMySQL();
            ArrayList<Usuario> usuarios = new ArrayList<>();
            PreparedStatement buscar = conexao.prepareStatement("select id_usu from papelusu where id_pap=?");
            buscar.setLong(1,p.getIdpap());
            ResultSet rs = buscar.executeQuery();
            while(rs.next()){
                usuarios.add(usuDAO.buscarUsuarioPorId(rs.getLong("idusu")));
            }

            return usuarios;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha ao buscar usuarios por papel");
        }
        return null;
    }

    @Override
    public ArrayList<Papel> buscarPapeisPorUsuario(Usuario u) {
        try{
            PapelDAOMySQL papelDAO = new PapelDAOMySQL();
            PreparedStatement buscar = conexao.prepareStatement("select id_pap from papelusu where id_usu=?");
            buscar.setLong(1,u.getId());
            ResultSet rs=buscar.executeQuery();
            System.out.println("Buscando papeis do usuario");
            while (rs.next()){
                long id=rs.getLong("id_pap");
                u.addPapel(papelDAO.BuscarporId(id).buscarPapel());

            }
            return u.getPapeis();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha em buscar Papeis por usuario");
        }
        return null;
    }

    @Override
    public void alterarPapeisdoUsuario(Usuario u) {
        try{
            excluirPapeisdoUsuario(u);
            inserirPapelUsuario(u.getId(),u.getPapeis());
            System.out.println("Papeis alterados com sucesso");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao alterar os papeis do usuario");
        }
    }

    @Override
    public void excluirPapeisdoUsuario(Usuario u) {
        try{
            PreparedStatement excluir = conexao.prepareStatement("delete from papelusu where id_usu =?");
            excluir.setLong(1,u.getId());
            excluir.executeUpdate();
            System.out.println("Papeis do usuario Excluidos");
            excluir.close();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Problema na hora de Excluir Papeis Usuarios");
        }
    }
}
