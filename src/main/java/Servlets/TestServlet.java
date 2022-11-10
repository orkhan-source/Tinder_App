package Servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

        String fileName = "src/main/resources/templates/login2.ftl";

        try(ServletOutputStream os = rs.getOutputStream()){
            Files.copy(Path.of(fileName), os);
        }

    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {


        try(PrintWriter w = rs.getWriter()){
            w.println("post method in TestServlet");
            w.println(rq.getContentType());
            w.println(rq.getParameter("email"));
        }


    }
}
