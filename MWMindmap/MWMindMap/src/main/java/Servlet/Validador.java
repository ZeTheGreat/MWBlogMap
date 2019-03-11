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

@WebServlet(name ="Validador", urlPatterns = {"validador"})
public class Validador extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();

        request.setCharacterEncoding("UTF-8");

        String nomeUsuario = request.getParameter("nomeUsuario");
        String senha = request.getParameter("senha");
        UsuarioDAOMySQL servicoUsuario = null;
        Usuario achado=null;
        try {
            servicoUsuario = new UsuarioDAOMySQL();
            achado = servicoUsuario.bucarUsuarioPorLogin(nomeUsuario);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        HttpSession sessao = request.getSession(true);

        if (achado != null && achado.getSenha().equals(senha) && achado.getLogin().equals(nomeUsuario)) {
            sessao.setAttribute("login", achado);
            doGet(request, response);
        }else {
            response.sendRedirect("login2");
        }


    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("home");
    }
}
