package com.github.alejojperez.library_project.modules.ui;

import com.github.alejojperez.library_project.core.contracts.IPresenter;
import com.github.alejojperez.library_project.modules.ui.presenters.DashboardPresenter;
import com.google.inject.AbstractModule;

public class DashboardModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        this.bind(IPresenter.class).to(DashboardPresenter.class);
    }
}
