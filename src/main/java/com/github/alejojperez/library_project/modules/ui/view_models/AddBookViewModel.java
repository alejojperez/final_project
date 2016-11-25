package com.github.alejojperez.library_project.modules.ui.view_models;

import com.github.alejojperez.library_project.App;
import com.github.alejojperez.library_project.modules.data.entities.Book;
import com.github.alejojperez.library_project.modules.data.repositories.BooksRepository;
import com.github.alejojperez.library_project.modules.ui.views.AddBookView;
import com.google.inject.Inject;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.ObservableRuleBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import de.saxsys.mvvmfx.utils.validation.ValidationStatus;
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

    public AddBookViewModel() {
        this.initializeValidation();
    }

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

    //<editor-fold desc="Validation">

    private ObservableRuleBasedValidator titleValidator;
    public ValidationStatus titleValidation() { return this.titleValidator.getValidationStatus(); }

    private ObservableRuleBasedValidator authorValidator;
    public ValidationStatus authorValidation() { return this.authorValidator.getValidationStatus(); }

    public CompositeValidator bookFormValidator;

    private void initializeValidation()
    {
        this.titleValidator = App.getCurrentDIModule().getInstance(ObservableRuleBasedValidator.class);
        this.titleValidator.addRule(this.title.isNotEmpty(), ValidationMessage.error("Title field is required."));

        this.authorValidator = App.getCurrentDIModule().getInstance(ObservableRuleBasedValidator.class);
        this.authorValidator.addRule(this.author.isNotEmpty(), ValidationMessage.error("Author field is required."));

        this.bookFormValidator = App.getCurrentDIModule().getInstance(CompositeValidator.class);
        this.bookFormValidator.addValidators(this.titleValidator, this.authorValidator);
    }

    //</editor-fold>
}
