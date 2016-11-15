package com.github.alejojperez.library_project.modules.ui.views;

import com.github.alejojperez.library_project.modules.ui.view_models.BookDetailViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
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
    @FXML
    public VBox vbPendingRequest;

    @InjectViewModel
    private BookDetailViewModel viewModel;

    public void initialize(URL location, ResourceBundle resources)
    {
        this.txtTitle.textProperty().bindBidirectional(this.viewModel.title);
        this.txtAuthor.textProperty().bindBidirectional(this.viewModel.author);
        this.txtCheckoutDate.textProperty().bindBidirectional(this.viewModel.checkoutDate);
        this.txtBorrower.textProperty().bindBidirectional(this.viewModel.borrower);
        this.txtPendingBorrower.textProperty().bindBidirectional(this.viewModel.pendingRequestBorrower);
        this.vbContainer.disableProperty().bind(this.viewModel.id.lessThanOrEqualTo(0));
        this.btnBorrow.disableProperty().bind(this.txtBorrower.textProperty().isEmpty());

        this.viewModel.status.addListener((obs, oldValue, newValue) -> {
            if(newValue != null) {
                this.btnBorrow.setVisible(true);

                if(newValue.equals("IN")) {
                    this.btnBorrow.setText("Checkout");
                    this.txtBorrower.setDisable(false);
                    this.vbPendingRequest.setDisable(true);
                } else if(newValue.equals("OUT")) {
                    this.btnBorrow.setText("Checkin");
                    this.txtBorrower.setDisable(true);
                    this.vbPendingRequest.setDisable(false);
                } else
                    this.btnBorrow.setVisible(false);
            } else {
                this.vbPendingRequest.setDisable(true);
                this.btnBorrow.setVisible(false);
            }
        });
    }

    public void checkInOrOutBook()
    {
        this.viewModel.getCheckInOrOutBookCommand().execute();
    }

    public void saveBookInfo()
    {
        this.viewModel.getEditBookCommand().execute();
    }

    public void placeRequest()
    {
        this.viewModel.getPlaceRequestBookCommand().execute();
    }
}
