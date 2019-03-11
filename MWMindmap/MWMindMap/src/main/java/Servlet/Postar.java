package Servlet;

import DAO.MySQL.PostDAOMySQL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@WebServlet(name = "Postar", urlPatterns = {"postar"}, loadOnStartup = 1)
public class Postar extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        String parecer = null;

        String titulo = request.getParameter("titulo");
        String texto = request.getParameter("texto");

        Model.Post npost = new Model.Post(titulo,texto);

        try {
            PostDAOMySQL postDAO=new PostDAOMySQL();
            postDAO.adicionarPost(npost);
            parecer = "Postagem adicionada com sucesso!";
        } catch (SQLException e) {
            parecer = "Erro ao adicionar postagem!";
            e.printStackTrace();
        }

        request.setAttribute("parecer", parecer);
        response.sendRedirect("home");
    }
}