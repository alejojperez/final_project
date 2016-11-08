package com.github.alejojperez.library_project.modules.ui.views;

import com.github.alejojperez.library_project.modules.data.entities.Book;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<Book> booksTable;

    @InjectViewModel
    private DashboardViewModel viewModel;

    @Inject
    private NotificationCenter notificationCenter;

    public void initialize(URL location, ResourceBundle resources)
    {
        this.initializeBooksTable();
        this.initializeNotificationListeners();
    }

    //region Helpers

    protected void loadBooks()
    {
        /**
         * Set the table content
         */
        this.booksTable.setItems(FXCollections.observableArrayList(this.viewModel.getBooks()));
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
    }

    //endregion
}
