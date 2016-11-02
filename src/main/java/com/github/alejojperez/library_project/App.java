/**
 * Created by Alejandro Perez on 11/1/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project;

import com.github.alejojperez.library_project.core.contracts.IPresenter;
import com.github.alejojperez.library_project.modules.ui.DashboardModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.saxsys.mvvmfx.guice.MvvmfxGuiceApplication;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App extends MvvmfxGuiceApplication
{
    /**
     * Global Stage (window)
     */
    private static Stage globalStage;
    public static Stage getGlobalStage()
    {
        return globalStage;
    }
    public static Stage setGlobalStage(Stage stage)
    {
        App.globalStage = stage;
        return App.getGlobalStage();
    }

    /**
     * Current Dependency Injector module
     */
    private static Injector currentDIModule;
    public static Injector getCurrentDIModule()
    {
        return App.currentDIModule;
    }
    public static Injector setCurrentDIModule(Injector module) { App.currentDIModule = module; return App.getCurrentDIModule(); }

    public static void main(String...args)
    {
        Application.launch(args);
    }

    public void startMvvmfx(Stage stage) throws Exception
    {
        App.setGlobalStage(stage);

        App.setCurrentDIModule( Guice.createInjector(new DashboardModule()) );
        App.getCurrentDIModule().getInstance(IPresenter.class).show();
    }
}

