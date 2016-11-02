/**
 * Created by Alejandro Perez on 11/1/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project.core.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection
{
    private Connection MySqlConnection;

    private static DBConnection ourInstance = new DBConnection();

    public static DBConnection getInstance()
    {
        return ourInstance;
    }

    private DBConnection() { }

    public Connection getMySqlConnection()
    {
        return MySqlConnection;
    }

    public void initialize()
    {
        if(this.MySqlConnection == null)
        {
            try {
                String location = "jdbc:mysql://"+Configuration.HOST+":"+Configuration.PORT+"/"+Configuration.DATABASE+"?"+Configuration.USE_SSL;
                this.MySqlConnection = DriverManager.getConnection(location, Configuration.USERNAME, Configuration.PASSWORD);
            } catch(Exception e) {
                System.err.println("Unable to connect to the database.");
                e.printStackTrace();
            }
        }
    }
}
