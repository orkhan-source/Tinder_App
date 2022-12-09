package Servlets;

import Dao.MessageDao;
import Dao.UserDao;
import Entities.Message;
import Entities.User;
import Services.CookieService;
import Services.MessageService;
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

public class MessageServlet extends HttpServlet {

    private final Connection connection;
    private MessageService messageService;
    private UserService userService;
    private CookieService ck;

    private FreemarkerEngine f = new FreemarkerEngine();

    public MessageServlet(Connection connection) {
        this.connection = connection;
        this.messageService = new MessageService(new MessageDao(connection));
        this.userService = new UserService(new UserDao(connection));
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        ck = new CookieService(rq, rs);
        int senId = Integer.parseInt(ck.getCookie().getValue());
        int recId = Integer.parseInt(rq.getParameter("recId"));
        List<Message> sentMsgs = messageService.getMessagesBetween(senId, recId);
        List<Message> receivedMsgs = messageService.getMessagesBetween(recId, senId);
        User receiver = userService.getUser(recId);
        HashMap<String, Object> data = new HashMap<>();
        data.put("sentMsgs", sentMsgs);
        data.put("receivedMsgs", receivedMsgs);
        data.put("receiver", receiver);
        f.render(rs, "chat.ftl", data);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        int senId = Integer.parseInt(ck.getCookie().getValue());
        int recId = Integer.parseInt(rq.getParameter("recId"));
        String msgContent = rq.getParameter("msgContent");
        if(msgContent != null && !msgContent.isBlank()){
            Message msg = new Message(senId, recId, msgContent);
            messageService.sendMessage(msg);
        }
        doGet(rq, rs);
    }
}
