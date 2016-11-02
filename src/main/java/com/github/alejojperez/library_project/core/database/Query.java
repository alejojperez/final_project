/**
 * Created by Alejandro Perez on 11/1/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project.core.database;

import java.sql.ResultSet;

public class Query
{
    private String query;

    public Query(String query)
    {
        this.query = query;
    }

    public ResultSet get() throws Exception
    {
        return DBConnection.getInstance().getMySqlConnection().createStatement().executeQuery(this.query);
    }
}
