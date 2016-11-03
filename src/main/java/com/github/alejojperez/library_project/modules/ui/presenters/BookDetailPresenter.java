package com.github.alejojperez.library_project.modules.ui.presenters;

import com.github.alejojperez.library_project.App;
import com.github.alejojperez.library_project.core.contracts.IPresenter;
import com.github.alejojperez.library_project.modules.ui.view_models.BookDetailViewModel;
import com.github.alejojperez.library_project.modules.ui.view_models.DashboardViewModel;
import com.github.alejojperez.library_project.modules.ui.views.BookDetailView;
import com.github.alejojperez.library_project.modules.ui.views.DashboardView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BookDetailPresenter implements IPresenter
{
    private long id;
    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public void show()
    {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Library Inventory: Book Detail");
        ViewTuple<BookDetailView, BookDetailViewModel> viewTuple = FluentViewLoader.fxmlView(BookDetailView.class).load();
        viewTuple.getViewModel().setId(this.getId());

        Parent root = viewTuple.getView();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
