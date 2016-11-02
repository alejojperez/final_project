/**
 * Created by Alejandro Perez on 11/1/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project.modules.data.Entities;

import com.github.alejojperez.library_project.core.database.Query;

public class AbstractEntity
{
    public Query query(String query)
    {
        return new Query(query);
    }
}
