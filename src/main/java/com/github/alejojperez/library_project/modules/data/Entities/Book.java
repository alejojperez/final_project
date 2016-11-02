/**
 * Created by Alejandro Perez on 11/1/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project.modules.data.Entities;

import java.sql.Date;

public class Book
{
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public long getId()
    {
        return this.id;
    }
    protected long id;

    public String getAuthor()
    {
        return this.author;
    }
    protected String author;

    public String getBorrower()
    {
        return this.borrower;
    }
    protected String borrower;

    public Date getCheckoutDate()
    {
        return this.checkoutDate;
    }
    protected Date checkoutDate;

    public boolean isPendingRequest()
    {
        return this.pendingRequest;
    }
    protected boolean pendingRequest;

    public Status getStatus()
    {
        return this.status;
    }
    protected Status status;

    public String getTitle()
    {
        return this.title;
    }
    protected String title;
}
