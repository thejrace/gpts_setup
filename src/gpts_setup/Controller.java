package gpts_setup;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Button uiInstallBtn;
    @FXML private TextField uiStaticDirPrefixInput;
    @FXML private Button uiStartDiagBtn;
    @FXML private Button uiUninstallBtn;
    @FXML private Label uiDiagNotf;
    @FXML private Label uiInstallNotf;

    @Override
    public void initialize(URL url, ResourceBundle rb ){

        uiInstallBtn.setOnMouseClicked( ev -> {
            String staticPrefixDirValue = uiStaticDirPrefixInput.getText();
            System.out.println(staticPrefixDirValue);
            if( !staticPrefixDirValue.equals("") ){
                Setup setup = new Setup();
                uiInstallNotf.textProperty().bind(setup.getStatusProp());
                setup.action(staticPrefixDirValue, new ActionCallback() {
                    @Override
                    public void success(String msg) {

                    }

                    @Override
                    public void error(String msg) {

                    }
                });
            }
        });

    }

}
