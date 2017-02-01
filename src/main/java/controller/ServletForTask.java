package controller;

import model.daos.Entity;
import model.daos.TasksDaoImpl;
import model.entities.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by acer on 31.12.2016.
 */
@WebServlet(name = "ServletForTask")
public class ServletForTask extends HttpServlet {
    private TasksDaoImpl tasksDao = new TasksDaoImpl(Servlet.SESSION, Entity.TASKS);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        //task.setContent(dataInContent);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("task_content", dataInContent);
            session.setAttribute("task", task);
            session.setAttribute("content", "task");
        }
        Servlet.redirectToUserJSP(request, response);
    }
}
