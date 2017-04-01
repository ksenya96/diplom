package controller;

import model.daos.Entity;
import model.daos.ThemesDaoImpl;
import model.entities.Theme;
import model.service.ModificationsForThemes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by acer on 12.12.2016.
 */
@WebServlet(name = "ServletForThemes")
public class ServletForThemes extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            if (action.indexOf('#') > -1)
                action = action.substring(0, action.indexOf('#'));
            HttpSession session = request.getSession(false);
            switch (action) {
                case "class":
                    int clazz = Integer.parseInt(request.getParameter("class"));
                    List<Theme> themes = ModificationsForThemes.getThemesByClass(clazz);
                    if (session != null) {
                        session.setAttribute("themes", themes);
                        session.setAttribute("clazz", clazz);
                        session.setAttribute("content", "themes");
                    }
                    Servlet.redirectToIndexJSP(request, response);
                    break;
            }
        }
    }
}
