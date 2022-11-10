package Servlets;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

public class LoginServlet extends HttpServlet {

    private final Connection connection;

    public LoginServlet(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        Configuration conf = new Configuration(Configuration.VERSION_2_3_30);
        conf.setDefaultEncoding(String.valueOf((StandardCharsets.UTF_8)));
        conf.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));


        try(PrintWriter w = rs.getWriter()){
            conf.getTemplate("login2.ftl").process( new Object() ,w);

        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

    }
}
