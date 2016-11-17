package com.github.alejojperez.library_project.modules.ui.presenters;

import com.github.alejojperez.library_project.App;
import com.github.alejojperez.library_project.core.contracts.IPresenter;
import com.github.alejojperez.library_project.modules.ui.view_models.DashboardViewModel;
import com.github.alejojperez.library_project.modules.ui.view_models.MasterViewModel;
import com.github.alejojperez.library_project.modules.ui.views.DashboardView;
import com.github.alejojperez.library_project.modules.ui.views.MasterView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MasterViewPresenter implements IPresenter
{
    public void show()
    {
        Stage stage = App.getGlobalStage();

        stage.setResizable(false);

        stage.setMinWidth(800);
        stage.setMinHeight(600);

        stage.setTitle("Library Inventory: Dashboard");
        ViewTuple<MasterView, MasterViewModel> viewTuple = FluentViewLoader.fxmlView(MasterView.class).load();

        Parent root = viewTuple.getView();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
