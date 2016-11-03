/**
 * Created by Alejandro Perez on 11/1/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project.modules.data.entities;

import java.sql.Date;

public class Book extends AbstractEntity
{
    public Book() { }

    public long id;
    public String author;
    public String borrower;
    public Date checkoutDate;
    public boolean pendingRequest;
    public String pendingRequestBorrower;
    public String status;
    public String title;

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getAuthor()
    {
        return this.author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getBorrower()
    {
        return this.borrower;
    }

    public void setBorrower(String borrower)
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

    public boolean isPendingRequest()
    {
        return this.pendingRequest;
    }

    public void setPendingRequest(boolean pendingRequest)
    {
        this.pendingRequest = pendingRequest;
    }

    public String getPendingRequestBorrower()
    {
        return this.pendingRequestBorrower;
    }

    public void setPendingRequestBorrower(String pendingRequestBorrower)
    {
        this.pendingRequestBorrower = pendingRequestBorrower;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
