package Servlets;

import Dao.LikeDao;
import Dao.UserDao;
import Entities.Like;
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

public class UserServlet extends HttpServlet {

    private final Connection connection;
    private UserService userService;
    private LikeService likeService;
    private final FreemarkerEngine f = new FreemarkerEngine();
    private CookieService ck;
    private static int counter = 0;
    public UserServlet(Connection connection) {
        this.connection = connection;
        this.userService = new UserService(new UserDao(connection));
        this.likeService = new LikeService(new LikeDao(connection));
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        ck = new CookieService(rq, rs);
        int id = Integer.parseInt(ck.getCookie().getValue());
        List<User> allUsers = userService.getAllUsers(id);
        User user = allUsers.get(counter++);
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", user);
        f.render(rs, "like-page.ftl", data);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

        ck = new CookieService(rq, rs);
        int likerId = Integer.parseInt(ck.getCookie().getValue());
        int likedId = Integer.parseInt(rq.getParameter("likedId"));
        Like like = new Like(likerId, likedId);

        if (rq.getParameter("like") != null) {
            likeService.addLike(like);
        }
        else if (rq.getParameter("dislike") != null){
            if(likeService.isLikeExist(like)){
                likeService.removeLike(likeService.getLikeId(like));
            }
        }


        if(counter == userService.getAllUsers(likerId).size()){
            counter = 0;
            rs.sendRedirect("/liked");
        }
        else{
            doGet(rq, rs);
        }
    }
}