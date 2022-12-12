import DB.DBConnection;
import Filter.LoginFilter;
import Servlets.*;
import org.eclipse.jetty.server.Dispatcher;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;


public class ServerApp {
    public static void main(String[] args) throws Exception {

        Connection connection = DBConnection.connect();

        ServletContextHandler handler = new ServletContextHandler();

        EnumSet<DispatcherType> dt = EnumSet.of(DispatcherType.REQUEST);
        handler.addFilter(LoginFilter.class,"/users", dt);
        handler.addFilter(LoginFilter.class,"/liked", dt);
        handler.addFilter(LoginFilter.class,"/message", dt);
        handler.addFilter(LoginFilter.class,"/logout", dt);

        handler.addServlet(StaticFileServlet.class, "/*");
        handler.addServlet(new ServletHolder(new RegisterServlet(connection)), "/reg");
        handler.addServlet(new ServletHolder(new LoginServlet(connection)), "/login");
        handler.addServlet(new ServletHolder(new UserServlet(connection)), "/users");
        handler.addServlet(new ServletHolder(new LikeServlet(connection)), "/liked");
        handler.addServlet(new ServletHolder(new MessageServlet(connection)), "/message");
        handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout");




        Server server = new Server(8080);
        server.setHandler(handler);
        server.start();
        server.join();

    }
}
