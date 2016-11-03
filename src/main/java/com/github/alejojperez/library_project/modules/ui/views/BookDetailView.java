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

public class BookDetailView implements FxmlView<BookDetailViewModel>, Initializable
{
    @InjectViewModel
    private BookDetailViewModel viewModel;

    @Inject
    private NotificationCenter notificationCenter;

    public void initialize(URL location, ResourceBundle resources)
    {
    }

}
