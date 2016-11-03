package com.github.alejojperez.library_project.modules.ui.presenters;

import com.github.alejojperez.library_project.App;
import com.github.alejojperez.library_project.core.contracts.IPresenter;
import com.github.alejojperez.library_project.modules.ui.view_models.DashboardViewModel;
import com.github.alejojperez.library_project.modules.ui.views.DashboardView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DashboardPresenter implements IPresenter
{
    public boolean screenSize = false;

    public void show()
    {
        Stage stage = App.getGlobalStage();

        stage.setResizable(true);

        //set Stage boundaries to visible bounds of the main screen
        if(this.screenSize)
            this.makeScreenSize(stage);

        stage.setMinWidth(800);
        stage.setMinHeight(600);

        stage.setTitle("Library Inventory: Dashboard");
        ViewTuple<DashboardView, DashboardViewModel> viewTuple = FluentViewLoader.fxmlView(DashboardView.class).load();

        Parent root = viewTuple.getView();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void makeScreenSize(Stage stage)
    {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
    }
}
