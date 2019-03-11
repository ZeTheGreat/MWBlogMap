package Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="Setings", urlPatterns = {"setings"})
public class Setings extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        HttpSession sessao = request.getSession(true);
        sessao.setAttribute("pagina", "setings");
        sc.getRequestDispatcher("/jsp/MasterPage.jsp").forward(request,response);
    }
}