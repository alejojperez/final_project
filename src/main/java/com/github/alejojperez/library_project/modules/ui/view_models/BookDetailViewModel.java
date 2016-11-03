package com.github.alejojperez.library_project.modules.ui.view_models;

import com.github.alejojperez.library_project.modules.data.entities.Book;
import com.github.alejojperez.library_project.modules.data.repositories.BooksRepository;
import com.google.inject.Inject;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;

import java.util.ArrayList;
import java.util.List;

public class BookDetailViewModel implements ViewModel
{
    public long getId()
    {
        return this.id;
    }
    public void setId(long id)
    {
        this.id = id;
    }
    private long id;

    @Inject
    private BooksRepository booksRepository;

    @Inject
    private NotificationCenter notificationCenter;

    public BookDetailViewModel() { }

    public Book getBook()
    {
        try {
            return this.booksRepository.find(this.getId());
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
