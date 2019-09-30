package pl.javastart.homebudget.factory;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionFactory {
    public Connection createConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        String url= "jdbc:mysql://localhost:3306/home_budget?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
        String username = "root";
        String password = "admin";

        return DriverManager.getConnection(url, username, password);
    }
}
