package DAO.MySQL;

import DAO.Interface.PapelDAO;
import Model.Papel;
import Model.PapelEnum;

import java.sql.*;
import java.util.ArrayList;

public class PapelDAOMySQL implements PapelDAO {

    private ConexaoMySQL conect ;
    private String table="create table if not exists Papel(\n" +
            "id_pap int primary key,\n" +
            "tipo_pap enum('ADM','USU','VIS')\n" +
            ");";
    private Connection conexao;

    public PapelDAOMySQL() throws SQLException {
        conect = new ConexaoMySQL();
        conexao= conect.getConection();
        conexao.createStatement().execute(table);
    }

    @Override
    public Papel BuscarporId(Long id) {
        try{
            PreparedStatement buscar = conexao.prepareStatement("select tipo_pap from papel where id_pap=?");
            buscar.setLong(1,id);
            ResultSet rs = buscar.executeQuery();
            System.out.println("Buscando papel por ID");
            rs.next();
            if(!rs.first())return null;
            else{
                String resp=rs.getString("tipo_pap");
                Papel papelresp = new Papel(id,PapelEnum.buscarEnum(resp));
                return papelresp;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha ao buscar papel por ID");
        }
        return null;
    }

    @Override
    public ArrayList<Papel> Buscartodos() {
        try{
            Papel papel =new Papel();
            ArrayList<Papel> papeis = new ArrayList<>();
            PreparedStatement buscar = conexao.prepareStatement("select * from papel");
            ResultSet rs = buscar.executeQuery();
            while(rs.next()){
                papel.setDescricao(PapelEnum.buscarEnum(rs.getString("tipo_pap")));
                papel.setIdpap(rs.getLong("id_pap"));
                papeis.add(papel);
            }
            buscar.close();
            rs.close();
            return papeis;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha em buscar todos Papeis");
        }
        return null;
    }

    @Override
    public void altrarPapel(Papel p, Papel n) {

    }
}
