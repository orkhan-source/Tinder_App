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

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class UserServlet extends HttpServlet {

    private final Connection connection;
    private UserService userService;
    private final FreemarkerEngine f = new FreemarkerEngine();
    private static int counter = 0;
    public UserServlet(Connection connection) {
        this.connection = connection;
        this.userService = new UserService(new UserDao(connection));
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        if(counter == userService.getAllUsers().size())
            counter = 0;
        List<User> allUsers = userService.getAllUsers();
        User user = allUsers.get(counter++);
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", user);

        f.render(rs, "like-page.ftl", data);

    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

        if(counter == userService.getAllUsers().size()){
            rs.sendRedirect("/liked");
        }

        doGet(rq, rs);
    }
}