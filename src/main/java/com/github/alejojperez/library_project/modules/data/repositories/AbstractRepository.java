/**
 * Created by Alejandro Perez on 11/2/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project.modules.data.repositories;

import com.github.alejojperez.library_project.core.database.DBConnection;
import com.github.alejojperez.library_project.modules.data.entities.AbstractEntity;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractRepository
{
    /**
     * @return
     * @throws Exception
     */
    protected Statement getStatement() throws Exception
    {
        return DBConnection.getInstance().getMySqlConnection().createStatement();
    }

    /**
     * @param query
     * @return
     * @throws Exception
     */
    protected int delete(String query) throws Exception
    {
        return this.getStatement().executeUpdate(query);
    }

    /**
     * @param query
     * @return
     * @throws Exception
     */
    protected int insert(String query) throws Exception
    {
        return this.getStatement().executeUpdate(query);
    }

    /**
     * @param query
     * @return
     * @throws Exception
     */
    protected ResultSet select(String query) throws Exception
    {
        return this.getStatement().executeQuery(query);
    }

    /**
     * @param query
     * @return
     * @throws Exception
     */
    protected int update(String query) throws Exception
    {
        return this.getStatement().executeUpdate(query);
    }
}
