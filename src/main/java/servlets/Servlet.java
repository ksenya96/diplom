package servlets;

import model.daos.DaoFactory;
import model.daos.Entity;
import model.utils.HibernateSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by acer on 11.08.2016.
 */
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("themes", DaoFactory.getDao(Entity.THEMES).getAllEntities());

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
