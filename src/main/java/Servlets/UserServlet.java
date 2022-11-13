package Servlets;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {


        Configuration conf = new Configuration(Configuration.VERSION_2_3_30);
        conf.setDefaultEncoding(String.valueOf((StandardCharsets.UTF_8)));
        conf.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));


        try(PrintWriter w = rs.getWriter()){
            conf.getTemplate("like-page2.ftl").process( new Object() ,w);

        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

    }
}
