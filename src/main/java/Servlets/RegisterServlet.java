package Servlets;

import Dao.UserDao;
import Entities.User;
import Services.UserService;
import Utils.FreemarkerEngine;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.HashMap;

public class RegisterServlet extends HttpServlet {
    private final Connection connection;
    private UserService userService;
    private final FreemarkerEngine f = new FreemarkerEngine();

    public RegisterServlet(Connection connection) {
        this.connection = connection;
        this.userService = new UserService(new UserDao(connection));
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        f.render(rs, "register.ftl", new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {


        String email = rq.getParameter("email");
        String password = rq.getParameter("password");
        String name = rq.getParameter("name");
        String surname = rq.getParameter("surname");
        String gender = rq.getParameter("gender");
        boolean genderB = gender.equalsIgnoreCase("male");
        String imgUrl = rq.getParameter("imgUrl");
        User user = new User(email, password, name, surname, genderB, imgUrl);

        userService.addUser(user);

        rs.sendRedirect("/login");
    }
}
