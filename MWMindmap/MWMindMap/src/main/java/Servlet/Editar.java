package Servlet;


import DAO.MySQL.UsuarioDAOMySQL;
import Model.Usuario;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Editar", urlPatterns = {"editar"}, loadOnStartup = 1)
public class Editar extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        HttpSession sessao = request.getSession();

        Long id = Long.parseLong(request.getParameter("usuarioId"));


        Usuario usuario = new Usuario();

        try {
            UsuarioDAOMySQL usudao= new UsuarioDAOMySQL();
            usuario = usudao.buscarUsuarioPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        sessao.setAttribute("usuarioalterar", usuario);

        sessao.setAttribute("pagina", "editar");
        sc.getRequestDispatcher("/jsp/MasterPage.jsp").forward(request,response);


    }
}