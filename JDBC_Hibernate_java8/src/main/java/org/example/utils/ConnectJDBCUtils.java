package org.example.utils;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectJDBCUtils {
    static final String DB_URL="jdbc:mysql://localhost:3306/manage_bus";
    static final String USER="root";
    static final String PASS="Hoanghiep2003@";

    public static Connection getConnection() {
        Connection conn=null;
        try {
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

        }catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
