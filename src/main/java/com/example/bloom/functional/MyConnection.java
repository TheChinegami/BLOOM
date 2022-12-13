package com.example.bloom.functional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static Connection con;

    private MyConnection() {

    }

    public static Connection getCon() throws ClassNotFoundException, SQLException
    {
        if(con==null)
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bloom","root","chanigui");
        }
        return con;
    }

}
