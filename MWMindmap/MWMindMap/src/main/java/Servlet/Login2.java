package Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name ="login2", urlPatterns = {"login2"})
public class Login2 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        HttpSession sessao = request.getSession(true);
        sessao.setAttribute("pagina", "login2");
        sc.getRequestDispatcher("/jsp/MasterPage.jsp").forward(request,response);
    }
}