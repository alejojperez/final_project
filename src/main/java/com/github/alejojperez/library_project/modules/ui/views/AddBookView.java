package com.github.alejojperez.library_project.modules.ui.views;

import com.github.alejojperez.library_project.modules.ui.view_models.AddBookViewModel;
import com.google.inject.Inject;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookView implements FxmlView<AddBookViewModel>, Initializable
{
    @FXML
    public TextField txtTitle;
    @FXML
    public TextField txtAuthor;
    @FXML
    public Label lblTitleValidationMessage;
    @FXML
    public Label lblAuthorValidationMessage;
    @FXML
    public Button btnAddBook;

    @InjectViewModel
    private AddBookViewModel viewModel;

    @Inject
    private NotificationCenter notificationCenter;

    public void initialize(URL location, ResourceBundle resources)
    {
        this.viewModel.title.bindBidirectional(this.txtTitle.textProperty());
        this.viewModel.author.bindBidirectional(this.txtAuthor.textProperty());

        this.notificationCenter.subscribe("module:ui:AddBook", (key, payload) -> {

        });

        /**
         * Configure title textfield validation
         */
        this.viewModel.titleValidation().getMessages().addListener((ListChangeListener<ValidationMessage>) c -> {
            String text = null;

            if(this.viewModel.titleValidation().getHighestMessage().isPresent())
                text = this.viewModel.titleValidation().getHighestMessage().get().getMessage();

            this.lblTitleValidationMessage.setText(text);
        });
        this.lblTitleValidationMessage.visibleProperty().bind(this.lblTitleValidationMessage.textProperty().isNotEmpty());
        this.txtTitle.disableProperty().bind(this.viewModel.getAddBookCommand().runningProperty());

        /**
         * Configure author textfield validation
         */
        this.viewModel.authorValidation().getMessages().addListener((ListChangeListener<ValidationMessage>) c -> {
            String text = null;

            if(this.viewModel.authorValidation().getHighestMessage().isPresent())
                text = this.viewModel.authorValidation().getHighestMessage().get().getMessage();

            this.lblAuthorValidationMessage.setText(text);
        });
        this.lblAuthorValidationMessage.visibleProperty().bind(this.lblAuthorValidationMessage.textProperty().isNotEmpty());
        this.txtAuthor.disableProperty().bind(this.viewModel.getAddBookCommand().runningProperty());

        /**
         * Configure add book button validation
         */
        this.btnAddBook.disableProperty().bind(this.viewModel.bookFormValidator.getValidationStatus().validProperty().not());
    }

    public void saveBook(ActionEvent event)
    {
        this.viewModel.getAddBookCommand().execute();
        this.btnAddBook.getScene().getWindow().hide();
    }
}
