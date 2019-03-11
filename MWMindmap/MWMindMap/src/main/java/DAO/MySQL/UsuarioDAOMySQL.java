package DAO.MySQL;

import DAO.Interface.UsuarioDAO;
import Model.Papel;
import Model.PapelEnum;
import Model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAOMySQL implements UsuarioDAO {

    private ConexaoMySQL conect ;
    private String table="create table if not exists Usuario(\n" +
            "id_usu int auto_increment primary key ,\n" +
            "nome_usu varchar(100),\n" +
            "senha_usu varchar(200),\n" +
            "login_usu varchar(100),\n" +
            "email_usu varchar(100)\n" +
            ");";
    private Connection conexao;

    public UsuarioDAOMySQL() throws SQLException {
        conect = new ConexaoMySQL();
        conexao= conect.getConection();
        conexao.createStatement().execute(table);
    }

    @Override
    public ArrayList<Usuario> buscarUsuarioPorNome(String nomeusu) {
        Usuario usub=null;
        try{
            ArrayList<Usuario> usus = new ArrayList<>();
            PreparedStatement busca = conexao.prepareStatement("select * from mwblog.usuario where nome_usuario = ?");
            busca.setString(1,nomeusu);
            ResultSet rs= busca.executeQuery();
            System.out.println("Buscando usuario por nome...");
            while(rs.next()){
                System.out.println("usuario achado");
                usub=new Usuario();
                usub.setId(rs.getLong("id_usu"));
                usub.setNome(rs.getString("nome_usu"));
                usub.setSenha(rs.getString("senha_usu"));
                usub.setLogin(rs.getString("login_usu"));
                usub.setEmail(rs.getString("email_usu"));

                usus.add(usub);
            }

            busca.close();
            rs.close();
            return usus;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro na hora de Puxar o Usuario");
        }
        return null;
    }

    @Override
    public Usuario buscarUsuarioPorId(long id) {

        try {
            UsuarioPapelDAOMySQL usupapelDAO = new UsuarioPapelDAOMySQL();
            PreparedStatement busca = conexao.prepareStatement("select * from mwblog.usuario where id_usu =?");
            Usuario usub = new Usuario();
            busca.setLong(1, id);
            ResultSet rs = busca.executeQuery();
            System.out.println("buscando usuario por id");
            rs.next();
            if (!rs.first()) return null;
            else {
                System.out.println("Perfil achado");
                usub=MontarUsu(rs);
                ArrayList<Papel> papeis = usupapelDAO.buscarPapeisPorUsuario(usub);
                usub.setPapeis(papeis);
                busca.close();
                rs.close();
                return usub;
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("erro em buscar usuario");
        }
        return null;
    }

    private Usuario MontarUsu(ResultSet rs) throws SQLException {
        System.out.println("Perfil achado");
        Usuario usub = new Usuario();
        usub.setId(rs.getLong("id_usu"));
        usub.setNome(rs.getString("nome_usu"));
        usub.setSenha(rs.getString("senha_usu"));
        usub.setLogin(rs.getString("login_usu"));
        usub.setEmail(rs.getString("email_usu"));
        return usub;
    }

    @Override
    public Usuario bucarUsuarioPorLogin(String login) {

        try {
            UsuarioPapelDAOMySQL usupapelDAO = new UsuarioPapelDAOMySQL();
            PreparedStatement busca = conexao.prepareStatement("select * from mwblog.usuario where login_usu =?");
            Usuario usub;
            busca.setString(1, login);
            ResultSet rs = busca.executeQuery();
            System.out.println("buscando usuario por login");
            rs.next();
            if (!rs.first()) return null;
            else {
                usub=MontarUsu(rs);
                ArrayList<Papel> papeis = usupapelDAO.buscarPapeisPorUsuario(usub);
                usub.setPapeis(papeis);
                busca.close();
                rs.close();
                return usub;
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("erro em buscar usuario por login");
        }
        return null;
    }

    public boolean ValidarUsuario(String login){
        try{
            String loginvalidador;
            PreparedStatement buscar = conexao.prepareStatement("select login_usu from usuario");
            ResultSet rs = buscar.executeQuery();
            while(rs.next()){
                loginvalidador=rs.getString("login_usu");
                if(loginvalidador.equals(login)){
                    System.out.println("login ja existe");
                    return false;
                }
            }
        }catch (Exception e){

        }
        return true;
    }

    @Override
    public void InserirUsuario(Usuario usuario) {
        try{
            PreparedStatement inserir = conexao.prepareStatement("insert into usuario (nome_usu, senha_usu, login_usu, email_usu) values (?,?,?,?)");
            inserir.setString(1,usuario.getNome());
            inserir.setString(2,usuario.getSenha());
            inserir.setString(3,usuario.getLogin());
            inserir.setString(4,usuario.getEmail());
            inserir.executeUpdate();

            Usuario usu =bucarUsuarioPorLogin(usuario.getLogin());

            UsuarioPapelDAOMySQL usupapel = new UsuarioPapelDAOMySQL();
            usupapel.inserirPapelUsuario(usu.getId(), usuario.getPapeis());

            System.out.println("Perfil cadastrado com sucesso");
            inserir.close();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Problema ao cadastrar o cliente");
        }
    }

    @Override
    public ArrayList<Usuario> listarUsuarios() {
        try {
            UsuarioPapelDAOMySQL usupapel = new UsuarioPapelDAOMySQL();
            ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
            PreparedStatement buscartodos = conexao.prepareStatement("select * from mwblog.usuario");
            ResultSet rs= buscartodos.executeQuery();
            System.out.println("Buscando todos usuarios");
            while(rs.next()){
                Usuario usu= new Usuario();
                usu.setId(rs.getLong("id_usu"));
                usu.setNome(rs.getString("nome_usu"));
                usu.setSenha(rs.getString("senha_usu"));
                usu.setLogin(rs.getString("login_usu"));
                usu.setEmail(rs.getString("email_usu"));
                usu.setPapeis(usupapel.buscarPapeisPorUsuario(usu));
                System.out.println(usu.getNome());
                usuarios.add(usu);

            }
            buscartodos.close();
            rs.close();
            return usuarios;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Falha em buscar todos Usuarios");
        }

        return null;
    }

    @Override
    public void ExcluirUsuario(Usuario usuario) {
        try{
            UsuarioPapelDAOMySQL usupapelDAO = new UsuarioPapelDAOMySQL();
            PostUsuDAOMySQL postusuDAO = new PostUsuDAOMySQL();
            ComentarioDAOMySQL comentDAO = new ComentarioDAOMySQL();
            comentDAO.deletarComentarioPorUsuario(usuario.getId());
            postusuDAO.excluirPostPorUsuario(usuario);
            usupapelDAO.excluirPapeisdoUsuario(usuario);
            PreparedStatement excluir = conexao.prepareStatement("delete from usuario where id_usu = ?");
            excluir.setLong(1,usuario.getId());
            excluir.executeUpdate();
            System.out.println("usuario excluido");
            excluir.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao excluir usuario");
        }
    }

    @Override
    public void AlterarUsuario(Usuario velhousuario, Usuario novousario) {
        try{
            UsuarioPapelDAOMySQL usupapelDAO = new UsuarioPapelDAOMySQL();
            usupapelDAO.alterarPapeisdoUsuario(novousario);
            PreparedStatement alterar = conexao.prepareStatement("update usuario set nome_usu = ?, login_usu=?, senha_usu=?" +
                    ",email_usu=? where id_usu =?");
            alterar.setLong(5,velhousuario.getId());
            alterar.setString(1,novousario.getNome());
            alterar.setString(2,novousario.getLogin());
            alterar.setString(3,novousario.getSenha());
            alterar.setString(4,novousario.getEmail());
            alterar.executeUpdate();
            System.out.println("Update realizado com sucesso no usuario"+velhousuario.getNome());
            alterar.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("erro ao alterar usuario");
        }
    }
}
