package com.github.alejojperez.library_project.modules.ui.views;

import com.github.alejojperez.library_project.App;
import com.github.alejojperez.library_project.modules.data.entities.Book;
import com.github.alejojperez.library_project.modules.ui.presenters.BookDetailPresenter;
import com.github.alejojperez.library_project.modules.ui.view_models.DashboardViewModel;
import com.google.inject.Inject;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
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
    public TableColumn<Book, String> tcBorrower;
    @FXML
    public TableColumn<Book, String> tcCheckoutDate;
    @FXML
    public TableColumn<Book, String> tcPendingRequest;
    @FXML
    public TableColumn<Book, String> tcPendingRequestBorrower;
    @FXML
    private TableView<Book> booksTable;

    @InjectViewModel
    private DashboardViewModel viewModel;

    @Inject
    private NotificationCenter notificationCenter;

    public void initialize(URL location, ResourceBundle resources)
    {
        this.initializeBooksTable();
    }

    //region Helpers

    protected void initializeBooksTable()
    {
        /**
         * Set the table content
         */
        this.booksTable.setItems(FXCollections.observableArrayList(this.viewModel.getBooks()));

        /**
         * Listen for row double click
         */
        this.booksTable.setOnMousePressed(event ->
        {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
            {
                Book selected = booksTable.getSelectionModel().getSelectedItem();
                BookDetailPresenter presenter = App.getCurrentDIModule().getInstance(BookDetailPresenter.class);
                presenter.setId(selected.getId());
                presenter.show();
            }
        });

        /**
         * Table Column Title
         */
        this.tcTitle.setCellValueFactory(
                (TableColumn.CellDataFeatures<Book, String> cell) -> new SimpleStringProperty(cell.getValue().getTitle())
        );

        /**
         * Table Column Author
         */
        this.tcAuthor.setCellValueFactory(
                (TableColumn.CellDataFeatures<Book, String> cell) -> new SimpleStringProperty(cell.getValue().getAuthor())
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
                                String color = book.getStatus().equals("IN") ? "1b9957" : "d9493e";
                                FontAwesomeIcon icon = book.getStatus().equals("IN") ? FontAwesomeIcon.SIGN_IN : FontAwesomeIcon.SIGN_OUT;
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

        /**
         * Table Column Borrower
         */
        this.tcBorrower.setCellValueFactory(
                (TableColumn.CellDataFeatures<Book, String> cell) -> new SimpleStringProperty(cell.getValue().getBorrower())
        );

        /**
         * Table Column Checkout Date
         */
        this.tcCheckoutDate.setCellValueFactory((TableColumn.CellDataFeatures<Book, String> cell) -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date date = cell.getValue().getCheckoutDate();


            return new SimpleStringProperty(date != null ? dateFormat.format(date) : "");
        });

        /**
         * Table Column Pending Request
         */
        this.tcPendingRequest.setCellFactory(
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
                                String color = book.isPendingRequest() ? "d9493e" : "1b9957";
                                FontAwesomeIcon icon = book.isPendingRequest() ? FontAwesomeIcon.CLOCK_ALT : FontAwesomeIcon.THUMBS_UP;
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

        /**
         * Table Column Pending Request Borrower
         */
        this.tcPendingRequestBorrower.setCellValueFactory(
                (TableColumn.CellDataFeatures<Book, String> cell) -> {
                    Book book = cell.getValue();
                    String text = book.isPendingRequest() ? book.getPendingRequestBorrower() : "N/A";
                    return new SimpleStringProperty(text);
                }
        );
    }

    //endregion
}
