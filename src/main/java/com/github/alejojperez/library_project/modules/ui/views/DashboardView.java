package com.github.alejojperez.library_project.modules.ui.views;

import com.github.alejojperez.library_project.App;
import com.github.alejojperez.library_project.modules.data.entities.Book;
import com.github.alejojperez.library_project.modules.ui.presenters.AddBookPresenter;
import com.github.alejojperez.library_project.modules.ui.view_models.DashboardViewModel;
import com.google.inject.Inject;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardView implements FxmlView<DashboardViewModel>, Initializable
{
    @FXML
    public TableColumn<Book, String> tcTitle;
    @FXML
    public TableColumn<Book, String> tcAuthor;
    @FXML
    public TableColumn<Book, String> tcStatus;
    @FXML
    public TableView<Book> booksTable;
    @FXML
    public Button btnLoadBooks;
    @FXML
    public TextField txtSearch;

    @InjectViewModel
    private DashboardViewModel viewModel;

    @Inject
    private NotificationCenter notificationCenter;

    public void initialize(URL location, ResourceBundle resources)
    {
        this.initializeBooksTable();
        this.initializeNotificationListeners();
        this.initializeBindings();
    }

    //region Helpers

    public void loadBooks()
    {
        this.txtSearch.setText("");

        ObservableList<Book> masterData = FXCollections.observableArrayList(this.viewModel.getBooks());

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Book> filteredData = new FilteredList<>(masterData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        this.txtSearch.textProperty().addListener((observable, oldValue, newValue) ->
        {
            filteredData.setPredicate(book -> {
                // If filter text is empty, display all books.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare title and author of every book with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (book.getTitle().getValue().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (book.getAuthor().getValue().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        this.booksTable.setItems(filteredData);
    }

    protected void initializeBindings()
    {
    }

    protected void initializeBooksTable()
    {
        this.loadBooks();

        /**
         * Listen for selection
         */
        this.booksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Book book = newSelection != null ? newSelection : new Book();
            this.viewModel.selectedBook().setValue(book);
        });

        /**
         * Table Column Title
         */
        this.tcTitle.setCellValueFactory(
                (TableColumn.CellDataFeatures<Book, String> cell) -> new SimpleStringProperty(cell.getValue().getTitle().getValue())
        );

        /**
         * Table Column Author
         */
        this.tcAuthor.setCellValueFactory(
                (TableColumn.CellDataFeatures<Book, String> cell) -> new SimpleStringProperty(cell.getValue().getAuthor().getValue())
        );

        /**
         * Table Column Status
         */
        this.tcStatus.setCellFactory(
                new Callback<TableColumn<Book, String>, TableCell<Book, String>>()
                {
                    @Override
                    public TableCell<Book, String> call(TableColumn<Book, String> param )
                    {
                        return new TableCell<Book, String>()
                        {
                            @Override
                            public void updateItem( String item, boolean empty )
                            {
                                super.updateItem( item, empty );
                                this.setText( null );

                                if ( empty ) {
                                    this.setGraphic( null );
                                } else {
                                    this.setGraphic( this.generateContent() );
                                }
                            }

                            private HBox generateContent()
                            {
                                Book book = this.getTableView().getItems().get(this.getIndex());

                                HBox hBox = new HBox();

                                /**
                                 * Status Icon
                                 */
                                String color = book.getStatus().getValue().equals("IN") ? "1b9957" : "d9493e";
                                FontAwesomeIcon icon = book.getStatus().getValue().equals("IN") ? FontAwesomeIcon.SIGN_IN : FontAwesomeIcon.SIGN_OUT;
                                FontAwesomeIconView iconView = new FontAwesomeIconView(icon);
                                iconView.setGlyphStyle("-fx-fill: #" + color);
                                hBox.getChildren().addAll(iconView);

                                /**
                                 * Add all components
                                 */
                                hBox.setSpacing(5);
                                hBox.setAlignment(Pos.CENTER);

                                return hBox;
                            }
                        };
                    }
                }
        );
    }

    protected void initializeNotificationListeners()
    {
        this.notificationCenter.subscribe("module:ui:CheckoutBook", (key, payload) -> {
            Platform.runLater(() -> initializeBooksTable());
        });

        this.notificationCenter.subscribe("module:ui:CheckinBook", (key, payload) -> {
            Platform.runLater(() -> initializeBooksTable());
        });

        this.notificationCenter.subscribe("module:ui:PendingRequestBook", (key, payload) -> {
            Platform.runLater(() -> initializeBooksTable());
        });

        this.notificationCenter.subscribe("module:ui:EditBook", (key, payload) -> {
            Platform.runLater(() -> initializeBooksTable());
        });

        this.notificationCenter.subscribe("module:ui:AddBook", (key, payload) -> {
            Platform.runLater(() -> initializeBooksTable());
        });

        this.notificationCenter.subscribe("module:ui:DeleteBook", (key, payload) -> {
            Platform.runLater(() -> initializeBooksTable());
        });
    }

    public void showAddWindow(ActionEvent actionEvent)
    {
        (App.getCurrentDIModule().getInstance(AddBookPresenter.class)).show();
    }

    //endregion
}
