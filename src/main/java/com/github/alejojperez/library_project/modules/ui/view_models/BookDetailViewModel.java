package com.github.alejojperez.library_project.modules.ui.view_models;

import com.github.alejojperez.library_project.modules.data.entities.Book;
import com.github.alejojperez.library_project.modules.data.repositories.BooksRepository;
import com.github.alejojperez.library_project.modules.ui.scopes.MasterDetailScope;
import com.google.inject.Inject;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BookDetailViewModel implements ViewModel
{
    @InjectScope
    private MasterDetailScope scope;

    @Inject
    private BooksRepository booksRepository;

    @Inject
    private NotificationCenter notificationCenter;

    public LongProperty id = new SimpleLongProperty();
    public StringProperty title = new SimpleStringProperty();
    public StringProperty author = new SimpleStringProperty();
    public StringProperty borrower = new SimpleStringProperty();
    public SimpleBooleanProperty pendingRequest = new SimpleBooleanProperty();
    public StringProperty pendingRequestBorrower = new SimpleStringProperty();
    public StringProperty status = new SimpleStringProperty();
    public StringProperty checkoutDate = new SimpleStringProperty();

    public void initialize() {
        scope.selectedBookProperty().addListener((obs, oldValue, newValue) -> {
            this.id.setValue(newValue.getId().getValue());
            this.title.setValue(newValue.getTitle().getValue());
            this.author.setValue(newValue.getAuthor().getValue());
            this.borrower.setValue(newValue.getBorrower().getValue());
            this.pendingRequest.setValue(newValue.isPendingRequest().getValue());
            this.pendingRequestBorrower.setValue(newValue.getPendingRequestBorrower().getValue());
            this.status.setValue(newValue.getStatus().getValue());

            if(newValue.getCheckoutDate() != null && newValue.getCheckoutDate().getTime() > 0)
            {
                SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
                this.checkoutDate.setValue(formater.format(newValue.getCheckoutDate()));
            }
            else
            {
                this.checkoutDate.setValue("");
            }
        });
    }

    //<editor-fold desc="CheckInOrOutBookCommand">

    private Command checkInOrOutBookCommand;

    public Command getCheckInOrOutBookCommand()
    {
        if(this.checkInOrOutBookCommand == null) {
            this.checkInOrOutBookCommand = new DelegateCommand(() -> new Action()
            {
                @Override
                protected void action() throws Exception
                {
                    long idl = id.getValue();
                    String notification = "";

                    if(status.getValue().equals("IN")) {

                        if(booksRepository.checkoutBook(idl, borrower.getValue()) > 0)
                            notification = "module:ui:CheckoutBook";
                    }
                    else if(status.getValue().equals("OUT")) {

                        if(booksRepository.checkingBook(idl) > 0)
                            notification = "module:ui:CheckinBook";
                    }

                    if(!notification.isEmpty())
                        notificationCenter.publish(notification, idl);
                }
            }, true);
        }

        return this.checkInOrOutBookCommand;
    }

    //</editor-fold>

    //<editor-fold desc="EditBookCommand">

    private Command editBookCommand;

    public Command getEditBookCommand()
    {
        if(this.editBookCommand == null) {
            this.editBookCommand = new DelegateCommand(() -> new Action()
            {
                @Override
                protected void action() throws Exception
                {
                    long idl = id.getValue();

                    if(booksRepository.editTitleAndAuthor(idl, title.getValue(), author.getValue()) > 0)
                        notificationCenter.publish("module:ui:EditBook", idl);
                }
            }, true);
        }

        return this.editBookCommand;
    }

    //</editor-fold>

    //<editor-fold desc="PlaceRequestBookCommand">

    private Command placeRequestBookCommand;

    public Command getPlaceRequestBookCommand()
    {
        if(this.placeRequestBookCommand == null) {
            this.placeRequestBookCommand = new DelegateCommand(() -> new Action()
            {
                @Override
                protected void action() throws Exception
                {
                    if(booksRepository.placePendingRequest(id.getValue(), pendingRequestBorrower.getValue()) > 0)
                        notificationCenter.publish("module:ui:PendingRequestBook", id.getValue());
                }
            }, true);
        }

        return this.placeRequestBookCommand;
    }

    //</editor-fold>

    //<editor-fold desc="DeleteBookCommand">

    private Command deleteBookCommand;

    public Command getDeleteBookCommand()
    {
        if(this.deleteBookCommand == null) {
            this.deleteBookCommand = new DelegateCommand(() -> new Action()
            {
                @Override
                protected void action() throws Exception
                {
                    if(booksRepository.deleteBook(id.getValue()) > 0)
                        notificationCenter.publish("module:ui:DeleteBook", id.getValue());
                }
            }, true);
        }

        return this.deleteBookCommand;
    }

    //</editor-fold>
}
