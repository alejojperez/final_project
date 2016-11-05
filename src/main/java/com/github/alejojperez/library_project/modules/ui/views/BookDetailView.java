package com.github.alejojperez.library_project.modules.ui.views;

import com.github.alejojperez.library_project.modules.data.entities.Book;
import com.github.alejojperez.library_project.modules.ui.view_models.BookDetailViewModel;
import com.github.alejojperez.library_project.modules.ui.view_models.DashboardViewModel;
import com.google.inject.Inject;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class BookDetailView implements FxmlView<BookDetailViewModel>, Initializable
{
    @FXML
    public TextField txtTitle;
    @FXML
    public TextField txtAuthor;
    @FXML
    public TextField txtCheckoutDate;
    @FXML
    public TextField txtBorrower;
    @FXML
    public Button btnBorrow;
    @FXML
    public TextField txtPendingBorrower;
    @FXML
    public Button btnPendingBorrower;
    @FXML
    public Button btnSave;
    @FXML
    public VBox vbContainer;

    @InjectViewModel
    private BookDetailViewModel viewModel;

    @Inject
    private NotificationCenter notificationCenter;

    public void initialize(URL location, ResourceBundle resources)
    {
        this.txtTitle.textProperty().bindBidirectional(this.viewModel.title);
        this.txtAuthor.textProperty().bindBidirectional(this.viewModel.author);
        this.txtCheckoutDate.textProperty().bindBidirectional(this.viewModel.checkoutDate);
        this.txtBorrower.textProperty().bindBidirectional(this.viewModel.borrower);
        this.txtPendingBorrower.textProperty().bindBidirectional(this.viewModel.pendingRequestBorrower);
        this.txtPendingBorrower.disableProperty().bind(this.viewModel.pendingRequest);
        this.btnPendingBorrower.disableProperty().bind(this.viewModel.pendingRequest);
        this.vbContainer.disableProperty().bind(this.viewModel.id.lessThanOrEqualTo(0));

        this.viewModel.status.addListener((obs, oldValue, newValue) -> {
            if(newValue != null) {
                this.btnBorrow.setVisible(true);

                if(newValue.equals("IN")) {
                    this.btnBorrow.setText("Checkout");
                    this.txtBorrower.setDisable(false);
                } else if(newValue.equals("OUT")) {
                    this.btnBorrow.setText("Checkin");
                    this.txtBorrower.setDisable(true);
                } else
                    this.btnBorrow.setVisible(false);
            } else {
                this.btnBorrow.setVisible(false);
            }
        });
    }

}
