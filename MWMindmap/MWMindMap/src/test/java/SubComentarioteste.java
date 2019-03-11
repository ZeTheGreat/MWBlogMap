import DAO.MySQL.SubComentarioDAOMySQL;
import Model.Comentario;
import Model.SubComentario;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class SubComentarioteste {
    SubComentario subcom;
    SubComentarioDAOMySQL subcomDAO;
    Comentario com;

    @Before
    public void setup() throws SQLException {
        subcom = new SubComentario();
        subcom.setIdusuario(1);
        subcom.setIdcomentario(1);
        subcom.setTexto("texto");
        subcom.setId(1);
        com= new Comentario();
        com.setId(1);
        com.setTexto("texto");
        com.setIdusuario(1);
        com.setIdpost(1);
        subcomDAO = new SubComentarioDAOMySQL();
    }
    @Test
    public void inserirsubcomentario(){
        subcomDAO.adicionarSubComentario(subcom);
    }
    @Test
    public void buscarcomentfilhosporcomentpai(){
        System.out.println(subcomDAO.buscarComentariosFilhosPorComentarioPai(com).get(0).getTexto());
    }
    @Test
    public void buscarUsudosubcomentario(){
        System.out.println(subcomDAO.buscarUsuDoSubComentario(subcom).getNome());
    }
    @Test
    public void alterarcomfilho(){
        SubComentario sub=subcom;
        sub.setTexto("NOVOTEXTO");
        subcomDAO.alterarSubComentario(subcom,sub);
    }
    @Test
    public void excluirsub(){
        subcomDAO.excluirSubComentario(subcom);
    }
    @Test
    public void excluirporusu(){
        subcomDAO.apagarComentariosFilhos(com);
    }
}
