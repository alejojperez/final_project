/**
 * Created by Alejandro Perez on 11/1/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project.modules.data.Entities;

import com.github.alejojperez.library_project.core.database.DBConnection;

import java.sql.ResultSet;

public class AbstractEntity
{


    public ResultSet query(String query) throws Exception
    {
        return DBConnection.getInstance().getMySqlConnection().createStatement().executeQuery(query);
    }
}
