package com.github.alejojperez.library_project.modules.ui.views;

import com.github.alejojperez.library_project.modules.ui.view_models.AddBookViewModel;
import com.google.inject.Inject;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookView implements FxmlView<AddBookViewModel>, Initializable
{
    @FXML
    public TextField txtTitle;
    @FXML
    public TextField txtAuthor;

    @InjectViewModel
    private AddBookViewModel viewModel;

    @Inject
    private NotificationCenter notificationCenter;

    public void initialize(URL location, ResourceBundle resources)
    {
        this.viewModel.title.bindBidirectional(this.txtTitle.textProperty());
        this.viewModel.author.bindBidirectional(this.txtAuthor.textProperty());

        this.notificationCenter.subscribe("module:ui:AddBook", (key, payload) -> {
            this.txtTitle.setText("");
            this.txtAuthor.setText("");
        });
    }

    public void saveBook()
    {
        this.viewModel.getAddBookCommand().execute();
    }
}
