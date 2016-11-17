package com.github.alejojperez.library_project.modules.ui;

import com.github.alejojperez.library_project.core.contracts.IPresenter;
import com.github.alejojperez.library_project.modules.ui.presenters.MasterViewPresenter;
import com.google.inject.AbstractModule;

public class MasterViewModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        this.bind(IPresenter.class).to(MasterViewPresenter.class);
    }
}
