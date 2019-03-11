package Servlet;

import DAO.MySQL.PostDAOMySQL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MostraPostagem", urlPatterns = {"mostrapostagem"}, loadOnStartup = 1)
public class MostrarPostagem extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();

        String strid = request.getParameter("postId");
        Long id = Long.parseLong(strid);

        System.out.println("id do post: " + id);

        Model.Post post = new Model.Post();
        try {
            PostDAOMySQL postDAO = new PostDAOMySQL();
            post = postDAO.buscarPorID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HttpSession seesion=request.getSession(true);
        seesion.setAttribute("postagem",post);
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("postagem");
    }
}
