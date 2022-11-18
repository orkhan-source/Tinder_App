package Servlets;

import Dao.LikeDao;
import Dao.UserDao;
import Entities.User;
import Services.CookieService;
import Services.LikeService;
import Services.UserService;
import Utils.FreemarkerEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LikeServlet extends HttpServlet {

    FreemarkerEngine f = new FreemarkerEngine();

    private LikeService likeService;
    private UserService userService;
    private CookieService ck;

    public LikeServlet(Connection connection) {
        this.likeService = new LikeService(new LikeDao(connection));
        this.userService = new UserService(new UserDao(connection));
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        ck = new CookieService(rq, rs);
        int id = Integer.parseInt(ck.getCookie().getValue());
        List<User> likedUsers = userService.getLikedUsers(id, likeService.getLikesFromUser(id));
        HashMap<String, Object> data = new HashMap<>();
        data.put("users", likedUsers);
        f.render(rs, "people-list.ftl", data);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

    }
}
