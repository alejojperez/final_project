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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractRepository
{
    abstract public Class getEntity() throws ClassNotFoundException;

    abstract public HashMap<String, String> getPropertiesDesc();

    /**
     * @param query
     * @return
     * @throws Exception
     */
    public ResultSet executeQuery(String query) throws Exception
    {
        return DBConnection.getInstance().getMySqlConnection().createStatement().executeQuery(query);
    }

    /**
     * @param query
     * @return
     * @throws Exception
     */
    public List query(String query) throws Exception
    {
        ResultSet resultSet = this.executeQuery(query);
        ArrayList<AbstractEntity> response = new ArrayList<>();

        while(resultSet.next())
        {
            Class itSelf = this.getEntity();
            AbstractEntity entity = (AbstractEntity)itSelf.newInstance();

            this.getPropertiesDesc().forEach((propertyName, propertyType) -> {

                try {
                    String propertyTypeMethod = "get"+propertyType;
                    Method method = resultSet.getClass().getMethod(propertyTypeMethod, String.class);
                    Object value = method.invoke(resultSet, propertyName);

                    this.invokeSetter(entity, propertyName, value);
                } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });

            response.add(entity);
        }

        return response;
    }

    //region Helpers

    private void invokeSetter(Object obj, String variableName, Object variableValue)
    {
        /* variableValue is Object because value can be an Object, Integer, String, etc... */
        try {
            /**
             * Get object of PropertyDescriptor using variable name and class
             * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
             */
            PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
            /* Set field/variable value using getWriteMethod() */
            objPropertyDescriptor.getWriteMethod().invoke(obj, variableValue);

        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | IntrospectionException e) {
            /* Java 8: Multiple exception in one catch. Use Different catch block for lower version. */
            e.printStackTrace();
        }
    }

    //endregion
}
