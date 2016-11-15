package com.github.alejojperez.library_project.modules.ui.view_models;

import com.github.alejojperez.library_project.modules.data.entities.Book;
import com.github.alejojperez.library_project.modules.data.repositories.BooksRepository;
import com.github.alejojperez.library_project.modules.ui.scopes.MasterDetailScope;
import com.google.inject.Inject;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.ObjectProperty;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel implements ViewModel
{
    @InjectScope
    private MasterDetailScope scope;

    @Inject
    private BooksRepository booksRepository;

    public ObjectProperty<Book> selectedBook() {
        return scope.selectedBookProperty();
    }

    public List getBooks()
    {
        List books = new ArrayList();

        try {
            books = this.booksRepository.all();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return books;
    }
}
