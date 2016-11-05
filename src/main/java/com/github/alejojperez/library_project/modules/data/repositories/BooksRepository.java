/**
 * Created by Alejandro Perez on 11/2/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project.modules.data.repositories;

import com.github.alejojperez.library_project.core.database.DBConnection;
import com.github.alejojperez.library_project.modules.data.entities.Book;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BooksRepository
{
    /**
     * @param query
     * @return
     * @throws Exception
     */
    public ResultSet query(String query) throws Exception
    {
        return DBConnection.getInstance().getMySqlConnection().createStatement().executeQuery(query);
    }

    public List all() throws Exception
    {
        ResultSet resultSet = this.query("Select * From books");
        ArrayList<Book> response = new ArrayList<>();

        while(resultSet.next())
        {
            Book entity = this.getBookObject(resultSet);
            response.add(entity);
        }

        return response;
    }

    public boolean checkingBook(long id) throws Exception
    {
        Book book = this.find(id);
        String query;

        if(book.isPendingRequest().getValue())
            query = "Update books Set borrower = pendingRequestBorrower, pendingRequest = 0, checkoutDate = CURDATE()  Where id = "+id;
        else
            query = "Update books Set status = 'IN', borrower = NULL Where id = "+id;


        ResultSet resultSet = this.query(query);

        return true;
    }

    public Book find(long id) throws Exception
    {
        ResultSet resultSet = this.query("Select * From books Where id = "+id);
        resultSet.next();
        return this.getBookObject(resultSet);
    }

    protected Book getBookObject(ResultSet resultSet) throws Exception
    {
        Book entity = new Book();
        entity.setId(new SimpleLongProperty(resultSet.getLong("id")));
        entity.setAuthor(new SimpleStringProperty(resultSet.getString("author")));
        entity.setBorrower(new SimpleStringProperty(resultSet.getString("borrower")));
        entity.setCheckoutDate(resultSet.getDate("checkoutDate"));
        entity.setPendingRequest(new SimpleBooleanProperty(resultSet.getBoolean("pendingRequest")));
        entity.setPendingRequestBorrower(new SimpleStringProperty(resultSet.getString("pendingRequestBorrower")));
        entity.setStatus(new SimpleStringProperty(resultSet.getString("status")));
        entity.setTitle(new SimpleStringProperty(resultSet.getString("title")));

        return entity;
    }
}
