package Servlet;

import DAO.MySQL.ComentarioDAOMySQL;
import Model.Comentario;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DelCom", urlPatterns = {"delcom"}, loadOnStartup = 1)
public class DelCom extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        HttpSession sessao = request.getSession();

        Long id = Long.parseLong( request.getParameter("comid"));

        Comentario comentario = new Comentario();

        try {
            ComentarioDAOMySQL comtdao = new ComentarioDAOMySQL();
            comtdao.deletarComentario(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("postagem");
    }
}
