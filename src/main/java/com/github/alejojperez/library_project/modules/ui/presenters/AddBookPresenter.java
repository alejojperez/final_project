package com.github.alejojperez.library_project.modules.ui.presenters;

import com.github.alejojperez.library_project.App;
import com.github.alejojperez.library_project.core.contracts.IPresenter;
import com.github.alejojperez.library_project.modules.ui.view_models.AddBookViewModel;
import com.github.alejojperez.library_project.modules.ui.view_models.MasterViewModel;
import com.github.alejojperez.library_project.modules.ui.views.AddBookView;
import com.github.alejojperez.library_project.modules.ui.views.MasterView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddBookPresenter implements IPresenter
{
    public void show()
    {
        Stage stage = new Stage();

        stage.setResizable(false);

        stage.setMinWidth(300);
        stage.setMinHeight(100);

        stage.setTitle("Library Inventory: Add Book");
        ViewTuple<AddBookView, AddBookViewModel> viewTuple = FluentViewLoader.fxmlView(AddBookView.class).load();

        Parent root = viewTuple.getView();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
