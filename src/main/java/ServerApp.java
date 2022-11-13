import DB.DBConnection;
import Servlets.LoginServlet;
import Servlets.RegisterServlet;
import Servlets.TestServlet;
import Servlets.UserServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.Connection;


public class ServerApp {
    public static void main(String[] args) throws Exception {

        Connection connection = DBConnection.connect();

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(UserServlet.class, "/users");
        handler.addServlet(new ServletHolder(new RegisterServlet(connection)), "/reg");
        handler.addServlet(new ServletHolder(new LoginServlet(connection)), "/login");



        Server server = new Server(8080);
        server.setHandler(handler);
        server.start();
        server.join();

    }
}
