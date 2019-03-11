package DAO.MySQL;

import DAO.Interface.CategoriaDAO;
import Model.Categoria;
import jdk.jfr.events.ExceptionThrownEvent;

import java.sql.*;
import java.util.ArrayList;

public class CategoriaDAOMySQL implements CategoriaDAO {



    private ConexaoMySQL conect ;


    private String table="create table if not exists Categoria(\n" +
            "id_cat int auto_increment primary key,\n" +
            "desc_cat varchar(20)\n" +
            ");";
    private Connection conexao;

    public CategoriaDAOMySQL() throws SQLException {
        conect = new ConexaoMySQL();
        conexao= conect.getConection();
        conexao.createStatement().execute(table);
    }
    private Categoria construircat(ResultSet rs) throws SQLException {
        Categoria catn= new Categoria();
        catn.setId(rs.getLong("id_cat"));
        catn.setDescricao(rs.getString("desc_cat"));
        return catn;
    }
    @Override
    public Categoria buscarPorNome(String nomecat) {
        try{
            Categoria catn=null;
            PreparedStatement busca = conexao.prepareStatement("select * from categoria where desc_cat = ?");
            busca.setString(1,nomecat);
            ResultSet rs=busca.executeQuery();
            while (rs.next()){
                catn=construircat(rs);
            }
            System.out.println("Categoria buscada");
            busca.close();
            rs.close();
            return catn;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao buscar categoria por nome");
        }
        return null;
    }

    @Override
    public Categoria buscarPorID(long id) {
        try{
            Categoria catn=null;
            PreparedStatement busca = conexao.prepareStatement("select * from categoria where id_cat = ?");
            busca.setLong(1,id);
            ResultSet rs=busca.executeQuery();
            while (rs.next()){
                catn= new Categoria();
                catn=construircat(rs);
            }
            System.out.println("Categoria buscada");
            busca.close();
            rs.close();
            return catn;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao buscar categoria por id");
        }
        return null;
    }

    @Override
    public ArrayList<Categoria> bucarTodas() {
        try{
            ArrayList<Categoria> categorias = new ArrayList<>();
            Categoria cataux = new Categoria();
            PreparedStatement busca = conexao.prepareStatement("select * from categoria");
            ResultSet rs=busca.executeQuery();
            while (rs.next()){
                cataux=construircat(rs);
                categorias.add(cataux);
            }
            System.out.println("Buscar todas categorias");
            busca.close();
            rs.close();
            return categorias;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao buscar todas categorias");
        }
        return null;
    }

    @Override
    public void inserirCategoria(Categoria cat) {
        try{
            PreparedStatement inserir = conexao.prepareStatement("insert into categoria (id_cat, desc_cat) " +
                    "values(?,?)");
            inserir.setLong(1,cat.getId());
            inserir.setString(2,cat.getDescricao());
            inserir.executeUpdate();
            System.out.println("Categoria inserida");
            inserir.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao inserir categoria");
        }
    }

    @Override
    public void alterarCategoria(Categoria catv, Categoria catn) {
        try{
            excluirCategoria(catv);
            inserirCategoria(catn);
            System.out.println("Categoria alterada com sucesso");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao alterar categoria");
        }
    }

    @Override
    public void excluirCategoria(Categoria cat) {
        try{
            PostCategoriaDAOMySQL postcatDAO = new PostCategoriaDAOMySQL();
            postcatDAO.excluirRelacaoCatPost(cat);
            PreparedStatement excluir = conexao.prepareStatement("delete from categoria where id_cat =?");
            excluir.setLong(1,cat.getId());
            excluir.executeUpdate();
            System.out.println("Categoria "+cat.getDescricao()+" excluida");
            excluir.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("falha ao excluir categoria");
        }
    }
}
