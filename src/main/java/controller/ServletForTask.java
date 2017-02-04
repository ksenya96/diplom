package controller;

import model.daos.Entity;
import model.daos.TasksDaoImpl;
import model.daos.UsersDaoImpl;
import model.entities.Task;
import model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;

/**
 * Created by acer on 31.12.2016.
 */
@WebServlet(value = "/task", name = "ServletForTask")
public class ServletForTask extends HttpServlet {
    private TasksDaoImpl tasksDao = new TasksDaoImpl(Servlet.SESSION, Entity.TASKS);
    private UsersDaoImpl usersDao = new UsersDaoImpl(Servlet.SESSION, Entity.USERS);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        Task task = (Task)session.getAttribute("task");
        user.getDoneTasks().add(task);
        usersDao.update(user);
        session.setAttribute("content", "theory");
        Servlet.redirectToUserJSP(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int taskId = Integer.parseInt(request.getParameter("task_id"));
        Task task = (Task) tasksDao.getEntityById(taskId);
        //получение содержимого файла
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(task.getContent())));
        String dataInContent = "";
        String s = reader.readLine();
        while (s != null) {
            dataInContent += s + '|';
            s = reader.readLine();
        }
        reader.close();
        dataInContent = dataInContent.substring(0, dataInContent.length() - 1);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("task_content", dataInContent);
            session.setAttribute("task", task);
            session.setAttribute("content", "task");
        }

        Servlet.redirectToUserJSP(request, response);

    }
}
