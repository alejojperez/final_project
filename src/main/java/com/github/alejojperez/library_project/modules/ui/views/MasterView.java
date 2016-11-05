package com.github.alejojperez.library_project.modules.ui.views;

import com.github.alejojperez.library_project.modules.ui.view_models.MasterViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class MasterView implements FxmlView<MasterViewModel>, Initializable
{
    @InjectViewModel
    private MasterViewModel viewModel;

    public void initialize(URL location, ResourceBundle resources)
    {
    }

}
