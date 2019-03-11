import DAO.MySQL.PapelDAOMySQL;
import Model.Papel;
import Model.PapelEnum;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class testepapel {
    Papel p;
    PapelEnum pe;
    PapelDAOMySQL pdao;

    @Before
    public void setup() throws SQLException {
        p= new Papel();
        p.setIdpap(1);
        pdao = new PapelDAOMySQL();
        p.setDescricao(pe.ADM);
    }
    @Test
    public void buscarporID(){
        System.out.println(pdao.BuscarporId(1L).buscarPapel());
    }
    @Test
    public  void buscartodos(){
        System.out.println(pdao.Buscartodos().get(0).buscarPapel());
    }
}
