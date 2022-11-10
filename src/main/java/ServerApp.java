import DB.DBConnection;
import Servlets.RegisterServlet;
import Servlets.TestServlet;
import Servlets.UserServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.Connection;


public class ServerApp {
    public static void main(String[] args) throws Exception {

        ServletContextHandler handler = new ServletContextHandler();

        Connection connection = DBConnection.connect();

        handler.addServlet(UserServlet.class, "/users");
        handler.addServlet(new ServletHolder(new RegisterServlet(connection)), "/reg");


        Server server = new Server(8080);
        server.setHandler(handler);
        server.start();
        server.join();

    }
}
