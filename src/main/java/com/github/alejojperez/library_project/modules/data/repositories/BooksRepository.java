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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BooksRepository extends AbstractRepository
{
    //<editor-fold desc="Helpers">

    /**
     * @param resultSet
     * @return
     * @throws Exception
     */
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

    //</editor-fold>

    public int addBook(String title, String author) throws Exception
    {
        String query = ("Insert Into books Set title = '"+title+"', author = '"+author+"' ");

        return this.insert(query);
    }

    /**
     * @return
     * @throws Exception
     */
    public List all() throws Exception
    {
        ResultSet resultSet = this.select("Select * From books");
        ArrayList<Book> response = new ArrayList<>();

        while(resultSet.next())
        {
            Book entity = this.getBookObject(resultSet);
            response.add(entity);
        }

        return response;
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public int checkingBook(long id) throws Exception
    {
        Book book = this.find(id);
        String query;

        if(book.isPendingRequest().getValue())
            query = "Update books Set borrower = pendingRequestBorrower, pendingRequestBorrower = NULL, pendingRequest = 0, checkoutDate = CURDATE()  Where id = "+id;
        else
            query = "Update books Set status = 'IN', borrower = NULL, checkoutDate = NULL Where id = "+id;

        return this.update(query);
    }

    /**
     * @param id
     * @param borrower
     * @return
     * @throws Exception
     */
    public int checkoutBook(long id, String borrower) throws Exception
    {
        String query = "Update books Set status = 'OUT', borrower = '"+borrower+"', checkoutDate = CURDATE() Where id = "+id;

        return this.update(query);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public int deleteBook(long id) throws Exception
    {
        String query = ("Delete From books Where id = "+id);

        return this.delete(query);
    }

    /**
     * @param id
     * @param title
     * @param auhtor
     * @return
     * @throws Exception
     */
    public int editTitleAndAuthor(long id, String title, String auhtor) throws Exception
    {
        String query = "Update books Set title = '"+title+"', author = '"+auhtor+"' Where id = "+id;

        return this.update(query);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public Book find(long id) throws Exception
    {
        ResultSet resultSet = this.select("Select * From books Where id = "+id);
        resultSet.next();
        return this.getBookObject(resultSet);
    }

    /**
     * @param id
     * @param pendingRequestBorrower
     * @return
     */
    public int placePendingRequest(long id, String pendingRequestBorrower) throws Exception
    {
        String query = "Update books Set pendingRequest = 1, pendingRequestBorrower = '"+pendingRequestBorrower+"' Where id = "+id;

        return this.update(query);
    }
}
