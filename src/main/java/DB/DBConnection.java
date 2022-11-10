package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String UNAME = "postgres";
    private static final String PWD = "postgres";

    private static Connection connection;


    public static Connection connect(){

        if(connection == null) {
            try {
                connection = DriverManager.getConnection(URL, UNAME, PWD);
            } catch (SQLException e) {
                throw new RuntimeException("Problem occurred during connecting to database ", e);
            }
        }

        return connection;
    }
}
