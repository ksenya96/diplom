package model;

import java.io.*;
import java.util.Date;

/**
 * Created by acer on 10.02.2017.
 */
public class Checker {
    public static boolean compile(String filePath) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("c:\\FPC\\2.6.0\\bin\\i386-win32\\ppc386", filePath);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        String result = getString(process.getInputStream());
        result = result.trim();
        result = result.substring(result.lastIndexOf('\n') + 1);
        return !"Fatal: Compilation aborted".equals(result);
    }

    private static String getString(InputStream inputStream) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        String result = "";
        while (line != null) {
            result += line + '\n';
            line = reader.readLine();
        }
        reader.close();
        return result;
    }

    public static TestAndComment test(String exe, File outputDirectory, File inputDirectory) throws IOException {
        File[] outputFiles = outputDirectory.listFiles();
        File outputFile;

        File[] inputFiles = null;
        File inputFile;
        if (inputDirectory != null) {
            inputFiles = inputDirectory.listFiles();
        }
        ProcessBuilder processBuilder;
        Process process;
        String result;
        String expected;
        for (int i = 0; i < outputFiles.length; i++) {
            outputFile = outputFiles[i];
            processBuilder = new ProcessBuilder(exe);
            process = processBuilder.start();
            String input = null;
            //ожидаемый вывод
            expected = getString(new FileInputStream(outputFile));
            if (inputFiles != null) {
                inputFile = inputFiles[i];
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                input = getString(new FileInputStream(inputFile));
                bufferedWriter.write(input);
                bufferedWriter.close();
            }
            //проверка на лимит времени (2 сек)
            Date currentDate = new Date();
            while ((new Date().getTime() - currentDate.getTime()) < 2000 && process.isAlive()) {}
            if (process.isAlive()) {
                //превышен лимит времени
                process.destroy();
                return new TestAndComment(i + 1, Comment.TIME_LIMIT, input, expected);
            }
            //ошибка времени выполнения
            if (process.exitValue() != 0)
                return new TestAndComment(i + 1, Comment.RUNTIME_ERROR, input, expected);
            result = getString(process.getInputStream());

            if (!expected.equals(result))
                return new TestAndComment(i + 1, Comment.WRONG_ANSWER, input, expected);

        }
        return new TestAndComment(0, Comment.OK);
    }
}
