/**
 * Created by Alejandro Perez on 11/1/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project.modules.data.entities;

import javafx.beans.property.*;

import java.sql.Date;

public class Book extends AbstractEntity
{
    public Book()
    {
        this.id = new SimpleLongProperty();
        this.author = new SimpleStringProperty();
        this.borrower = new SimpleStringProperty();
        this.checkoutDate = new Date(0);
        this.pendingRequest = new SimpleBooleanProperty();
        this.pendingRequestBorrower = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.title = new SimpleStringProperty();
    }

    public LongProperty id;
    public StringProperty author;
    public StringProperty borrower;
    public Date checkoutDate;
    public BooleanProperty pendingRequest;
    public StringProperty pendingRequestBorrower;
    public StringProperty status;
    public StringProperty title;

    public LongProperty getId()
    {
        return this.id;
    }

    public void setId(LongProperty id)
    {
        this.id = id;
    }

    public StringProperty getAuthor()
    {
        return this.author;
    }

    public void setAuthor(StringProperty author)
    {
        this.author = author;
    }

    public StringProperty getBorrower()
    {
        return this.borrower;
    }

    public void setBorrower(StringProperty borrower)
    {
        this.borrower = borrower;
    }

    public Date getCheckoutDate()
    {
        return this.checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate)
    {
        this.checkoutDate = checkoutDate;
    }

    public BooleanProperty isPendingRequest()
    {
        return this.pendingRequest;
    }

    public void setPendingRequest(BooleanProperty pendingRequest)
    {
        this.pendingRequest = pendingRequest;
    }

    public StringProperty getPendingRequestBorrower()
    {
        return this.pendingRequestBorrower;
    }

    public void setPendingRequestBorrower(StringProperty pendingRequestBorrower)
    {
        this.pendingRequestBorrower = pendingRequestBorrower;
    }

    public StringProperty getStatus()
    {
        return this.status;
    }

    public void setStatus(StringProperty status)
    {
        this.status = status;
    }

    public StringProperty getTitle()
    {
        return this.title;
    }

    public void setTitle(StringProperty title)
    {
        this.title = title;
    }
}
