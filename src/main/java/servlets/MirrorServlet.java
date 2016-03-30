package servlets;

import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by FomeIV on 30.03.2016.
 */
public class MirrorServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

//        Map<String, Object> pageVariables = createPageVariablesMap(request);
        Map<String, Object> pageVariables = new HashMap<>();
        Map<String,String[]> pageParams = request.getParameterMap();
        pageParams.forEach((n,y) -> pageVariables.put(n, Arrays.asList(y).stream().collect(Collectors.joining(" "))));

        pageVariables.put("message", "Test Message");

        response.getWriter().println(PageGenerator.instance().getPage("mirror.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }


    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        pageVariables.put("contextPath", request.getContextPath());
        return pageVariables;
    }

}
