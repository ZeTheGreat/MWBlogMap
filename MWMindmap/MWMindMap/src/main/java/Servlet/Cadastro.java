package Servlet;

import DAO.MySQL.UsuarioDAOMySQL;
import Model.Papel;
import Model.PapelEnum;
import Model.Usuario;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "Cadastro", urlPatterns = {"cadastro"}, loadOnStartup = 1)
public class Cadastro extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();

        String usuario = request.getParameter("nomeUsuario");
        String senha = request.getParameter("senha");
        String login = request.getParameter("apelidoUsuario");
        String email = request.getParameter("emailUsuario");
        ArrayList<Papel> papeis = new ArrayList<>();
        Papel papel = new Papel();
        papel.setDescricao(PapelEnum.USU);
        papel.setIdpap(2);
        papeis.add(papel);
        Usuario novoUsuario = new Usuario(usuario, login, senha, email,papeis);
        Usuario uaux=null;

        UsuarioDAOMySQL servicoUsuario = null;
        try {
            servicoUsuario = new UsuarioDAOMySQL();
            uaux=servicoUsuario.bucarUsuarioPorLogin(novoUsuario.getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (uaux==null) {
            try{
            servicoUsuario.InserirUsuario(novoUsuario);}
            catch (Exception e){
            e.printStackTrace();
        }
            response.sendRedirect("home");
        } else {
            request.getSession(true).setAttribute("2cad",true);
            response.sendRedirect("cadastrar");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}