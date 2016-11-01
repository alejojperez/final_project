package com.github.alejojperez.library_project.modules.ui.view_models;

import com.google.inject.Inject;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;

public class DashboardViewModel implements ViewModel
{
    @Inject
    private NotificationCenter notificationCenter;

    public DashboardViewModel()
    {
    }

    //<editor-fold desc="InitializeDestroyPinCommand">

//    private Command initializeDestroyPinCommand;
//
//    public Command getInitializeDestroyPinCommand(String pinKey)
//    {
//        IPin pin = this.controller.get(pinKey);
//
//        if(this.initializeDestroyPinCommand == null) {
//            this.initializeDestroyPinCommand = new DelegateCommand(() -> new Action()
//            {
//                @Override
//                protected void action() throws Exception
//                {
//                    String notification = pin.isInitialized() ? "module:dashboard:pinInitialized" : "module:dashboard:pinDestroyed";
//
//                    if(pin.isInitialized())
//                        pin.destroy();
//                    else
//                        pin.initialize();
//
//                    notificationCenter.publish(notification, pin);
//                }
//            }, true);
//        }
//
//        return this.initializeDestroyPinCommand;
//    }

    //</editor-fold>
}
