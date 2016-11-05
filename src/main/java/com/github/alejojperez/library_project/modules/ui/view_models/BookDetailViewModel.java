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

            if(newValue.getCheckoutDate() != null)
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

    //<editor-fold desc="CheckinBookCommand">

    private Command checkinBookCommand;

    public Command getCheckinBookCommand()
    {
        if(this.checkinBookCommand == null) {
            this.checkinBookCommand = new DelegateCommand(() -> new Action()
            {
                @Override
                protected void action() throws Exception
                {
                    long idl = id.getValue();
                    booksRepository.checkingBook(idl);

                    notificationCenter.publish("module:ui:CheckinBookCommand", idl);
                }
            }, true);
        }

        return this.checkinBookCommand;
    }

    //</editor-fold>
}
