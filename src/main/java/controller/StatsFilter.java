package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.*;

/**
 * Created by acer on 27.02.2017.
 */
@WebFilter(filterName = "StatsFilter")
public class StatsFilter implements Filter {

    //кол-во вызовов по часам
    private TreeMap<Date, Long> numberOfCalls = new TreeMap<>();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //получаем текущее время и выбрасываем миллисекунды, секудунды и минуты, т.к. нам нужно только время
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date currentDate = calendar.getTime();

        //добавляем либо обнавляем запись по текущей дате
        if (numberOfCalls.containsKey(currentDate)) {
            long calls = numberOfCalls.get(currentDate);
            numberOfCalls.put(currentDate, calls + 1);
        }
        else {
            numberOfCalls.put(currentDate, 1L);
        }

        //если записей больше 24, то удаляем первую запись, после чего записей станет 24 или меньше
        if (numberOfCalls.size() > 24) {
            numberOfCalls.remove(numberOfCalls.firstKey());
        }

        //получаем данные по часам за последние сутки (если запись отсутствует, пишем 0)
        TreeMap<Date, Long> result = new TreeMap<>();
        for (int i = 0; i <= 23; i++) {
            Date prevDate = new Date(currentDate.getTime() - i * 60 * 60 * 1000);
            if (numberOfCalls.containsKey(prevDate))
                result.put(prevDate, numberOfCalls.get(prevDate));
            else
                result.put(prevDate, 0L);
        }

        //выводим данные и передаем для дальнейшей обработки
        for (Map.Entry<Date, Long> entry: result.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        req.setAttribute("numberOfCalls", result);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
