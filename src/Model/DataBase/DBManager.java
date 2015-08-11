package Model.DataBase;


import java.sql.*;

public class DBManager {
//    private static final String url = "jdbc:mysql://localhost:3306/test";
//    private static final String user = "root";
//    private static final String password = "Rwhrtyw1";

    private final String url;
    private final String user;
    private final String password;

    private static DBManager instance;

    private DBManager(final String url, final String user, final String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static DBManager getInstance() {
        if (instance == null) {
//            instance = new DBManager();
        }
        return instance;
    }

    private ResultSet sendQueryRes(String query) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            return resultSet;
        }
    }

    private void sendQueryWithoutRes(String query) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
        }
    }


}
