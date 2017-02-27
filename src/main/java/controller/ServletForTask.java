package controller;

import model.Checker;
import model.Comment;
import model.TestAndComment;
import model.daos.Entity;
import model.daos.TasksDaoImpl;
import model.daos.UsersDaoImpl;
import model.entities.Task;
import model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by acer on 31.12.2016.
 */
@WebServlet(value = "/task", name = "ServletForTask")
@MultipartConfig(location = "d:\\")
public class ServletForTask extends HttpServlet {
    private TasksDaoImpl tasksDao = new TasksDaoImpl(Servlet.SESSION, Entity.TASKS);
    private UsersDaoImpl usersDao = new UsersDaoImpl(Servlet.SESSION, Entity.USERS);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        Task task = (Task)session.getAttribute("task");
        Part filePart = request.getPart("file");


        String fileName = "d:\\" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        Files.deleteIfExists(new File(fileName).toPath());
        Files.createFile(new File(fileName).toPath());
        String fileContent = getStringFromFile(filePart.getInputStream());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        writer.write(fileContent);
        writer.close();


        String dataInContent = getStringFromFile(new FileInputStream(task.getContent()));
        String[] elements = dataInContent.split("\\n\\n");
        File outputDirectory = new File(elements[1].trim());
        File inputDirectory = null;
        if (elements.length > 2)
            inputDirectory = new File(elements[2].trim());
        if (task.getType() == TaskType.PROGRAM) {
            if (Checker.compile(fileName)) {
                TestAndComment testAndComment = Checker.test(fileName.replace(".pas", ".exe"), outputDirectory, inputDirectory);
                int test = testAndComment.getTest();
                String input = testAndComment.getInput();
                String expected = testAndComment.getOutput();
                Comment comment = testAndComment.getComment();
                switch (comment) {
                    case OK:
                        session.setAttribute("result", "Программа прошла все тесты");
                        user.getDoneTasks().add(task);
                        usersDao.update(user);
                        break;
                    case RUNTIME_ERROR:
                        session.setAttribute("result", "Ошибка времени выполнения (логическая ошибка)");
                        break;
                    case TIME_LIMIT:
                        session.setAttribute("result", "Превышен лимит времени (программа выполнялась слишком долго)");
                        break;
                    default:
                        session.setAttribute("result", "Неверный ответ на тесте №" + test);
                }
                session.setAttribute("input", input);
                session.setAttribute("expected", expected);
            }
            else {
                session.setAttribute("result", "Ошибка компиляции (в файле содержится синтаксическая ошибка)");
            }


        }
        if (task.getType() == TaskType.ROBOT) {
            user.getDoneTasks().add(task);
            usersDao.update(user);
            session.setAttribute("content", "theory");
        }
        Servlet.redirectToUserJSP(request, response);
    }

    private String getStringFromFile(InputStream inputStream) throws IOException {
        String dataInContent = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String s = reader.readLine();
        while (s != null) {
            dataInContent += s + '\n';
            s = reader.readLine();
        }
        reader.close();
        return dataInContent;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int taskId = Integer.parseInt(request.getParameter("task_id"));
        Task task = (Task) tasksDao.getEntityById(taskId);
        String dataInContent = "";

        //получение содержимого файла
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(task.getContent())));
        String s = reader.readLine();
        while (s != null && !s.equals("")) {
            if (task.getType() == TaskType.ROBOT)
                dataInContent += s + '|';
            else
                dataInContent += s + '\n';
            s = reader.readLine();
        }
        reader.close();
        dataInContent = dataInContent.substring(0, dataInContent.length() - 1);


        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("task_content", dataInContent);
            session.setAttribute("task", task);
            session.setAttribute("result", "");
            session.setAttribute("content", "task");
        }

        Servlet.redirectToUserJSP(request, response);

    }
}
