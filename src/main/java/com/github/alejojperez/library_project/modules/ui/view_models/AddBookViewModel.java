package com.github.alejojperez.library_project.modules.ui.view_models;

import com.github.alejojperez.library_project.modules.data.entities.Book;
import com.github.alejojperez.library_project.modules.data.repositories.BooksRepository;
import com.google.inject.Inject;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddBookViewModel implements ViewModel
{
    @Inject
    private BooksRepository booksRepository;

    @Inject
    private NotificationCenter notificationCenter;

    public StringProperty title = new SimpleStringProperty();

    public StringProperty author = new SimpleStringProperty();

    //<editor-fold desc="AddBookCommand">

    private Command addBookCommand;

    public Command getAddBookCommand()
    {
        if(this.addBookCommand == null) {
            this.addBookCommand = new DelegateCommand(() -> new Action()
            {
                @Override
                protected void action() throws Exception
                {
                    if(booksRepository.addBook(title.getValue(), author.getValue()) > 0)
                        notificationCenter.publish("module:ui:AddBook");
                }
            }, true);
        }

        return this.addBookCommand;
    }

    //</editor-fold>
}
