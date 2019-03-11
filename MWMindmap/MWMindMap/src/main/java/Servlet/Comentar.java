package Servlet;

import DAO.Interface.ComentarioDAO;
import DAO.MySQL.ComentarioDAOMySQL;
import Model.Comentario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Comentar", urlPatterns = {"comentar"}, loadOnStartup = 1)
public class Comentar extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException {
            String parecer=null;
            String comentario=request.getParameter("comentario");
            long idPostagem = Long.parseLong(request.getParameter("idPostagem"));
            long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
            Comentario coment = new Comentario(idUsuario, idPostagem, comentario);
        try {
            ComentarioDAO comentDAO = new ComentarioDAOMySQL();
            comentDAO.criarComentario(coment.getIdpost(),coment.getIdusuario(),coment.getTexto());
        } catch (SQLException e) {
            e.printStackTrace();
            parecer = "Erro ao adicionar comentario!";
        }
        request.setAttribute("parecer", parecer);
        HttpSession sessao = request.getSession();
        Model.Post postagem = (Model.Post) sessao.getAttribute("postagem");
        request.setAttribute("postagem", postagem);
        request.setAttribute("postId", idPostagem);
        resp.sendRedirect("postagem");
    }
}
