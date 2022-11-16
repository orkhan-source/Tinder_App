package Servlets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
        Path path;
        if(!rq.getPathInfo().startsWith("/css")) path = Paths.get("src/main/resources/templates/css", rq.getPathInfo());
        else path = Paths.get("src/main/resources/templates", rq.getPathInfo());
        try (ServletOutputStream os = rs.getOutputStream()) {
            Files.copy(path, os);
        }
    }
}
