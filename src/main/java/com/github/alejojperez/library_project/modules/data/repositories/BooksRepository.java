/**
 * Created by Alejandro Perez on 11/2/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project.modules.data.repositories;

import com.github.alejojperez.library_project.modules.data.entities.Book;

import java.util.HashMap;
import java.util.List;

public class BooksRepository extends AbstractRepository
{
    @Override
    public Class getEntity() throws ClassNotFoundException
    {
        return Class.forName(Book.class.getName());
    }

    @Override
    public HashMap<String, String> getPropertiesDesc()
    {
        HashMap<String, String> response = new HashMap<>();
        response.put("id", "Long");
        response.put("author", "String");
        response.put("borrower", "String");
        response.put("checkoutDate", "Date");
        response.put("pendingRequest", "Boolean");
        response.put("pendingRequestBorrower", "String");
        response.put("status", "String");
        response.put("title", "String");
        return response;
    }

    public List all() throws Exception
    {
        return this.query("Select * From books");
    }
}
