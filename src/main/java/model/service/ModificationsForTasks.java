package model.service;

import controller.Servlet;
import controller.TaskType;
import model.daos.Entity;
import model.daos.TasksDaoImpl;
import model.daos.ThemesDaoImpl;
import model.daos.UsersDaoImpl;
import model.entities.Task;
import model.entities.Theme;
import model.entities.User;
import model.testingSystem.Checker;
import model.testingSystem.Comment;
import model.testingSystem.TestAndComment;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by acer on 01.04.2017.
 */
public class ModificationsForTasks {
    private static TasksDaoImpl tasksDao = new TasksDaoImpl(Servlet.SESSION, Entity.TASKS);
    private static UsersDaoImpl usersDao = new UsersDaoImpl(Servlet.SESSION, Entity.USERS);

    public static Task getTaskById(int id) {
        return (Task) tasksDao.getEntityById(id);
    }

    public static String getTaskContent(Task task) throws IOException {
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
        return dataInContent;
    }

    public static TestAndComment checkTask(User user, Task task, Part filePart) throws IOException {
        if (task.getType() == TaskType.PROGRAM) {
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
            if (Checker.compile(fileName)) {
                TestAndComment testAndComment = Checker.test(fileName.replace(".pas", ".exe"), outputDirectory, inputDirectory);
                if (testAndComment.getComment() == Comment.OK) {
                    user.getDoneTasks().add(task);
                    usersDao.update(user);
                }
                return testAndComment;

            }
            else {
                return new TestAndComment(-1, Comment.COMPILATION_ERROR);
            }
        }
        if (task.getType() == TaskType.ROBOT) {
            user.getDoneTasks().add(task);
            usersDao.update(user);
        }
        return null;

    }

    private static String getStringFromFile(InputStream inputStream) throws IOException {
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

}
