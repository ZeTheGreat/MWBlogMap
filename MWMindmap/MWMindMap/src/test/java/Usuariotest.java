import DAO.MySQL.UsuarioDAOMySQL;
import Model.Papel;
import Model.PapelEnum;
import Model.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class Usuariotest {
    Usuario usu;
    UsuarioDAOMySQL usudao;
    ArrayList<Papel> papeis;
    Papel papel1,papel2;
    @Before
    public void setup() throws SQLException {
        usu= new Usuario();
        usudao=new UsuarioDAOMySQL();
        papeis= new ArrayList<>();
        papel1= new Papel();
        papel2= new Papel();
        papel1.setDescricao(PapelEnum.ADM);
        papel1.setIdpap(1);
        papel2.setDescricao(PapelEnum.USU);
        papel2.setIdpap(2);
    }

    @Test
    public void getpapel(){
        usu=usudao.bucarUsuarioPorLogin("1234");
        System.out.println(usu.getPapeis());
    }
    @Test
    public void testao(){

    }
    @Test
    public void inserirusu(){
        papeis.add(papel1);
        papeis.add(papel2);
        Usuario usu= new Usuario(1,"José2","1234","123","Jose@jose.com",papeis);
        usudao.InserirUsuario(usu);
    }
    @Test
    public void listarusu(){
        System.out.println(usudao.listarUsuarios().get(0).toString());
    }
    @Test
    public void excluirusu(){
        papeis.add(papel1);
        papeis.add(papel2);
        Usuario usu= new Usuario(1,"José2","1234","123","Jose@jose.com",papeis);
        usudao.ExcluirUsuario(usu);
    }
}
