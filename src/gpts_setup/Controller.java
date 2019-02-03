/* Gitaş - Obarey Inc 2018 */
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
    @FXML private Label uiUninstallNotf;

    @Override
    public void initialize(URL url, ResourceBundle rb ){

        uiInstallBtn.setOnMouseClicked( ev -> {
            String staticPrefixDirValue = uiStaticDirPrefixInput.getText();
            if( !staticPrefixDirValue.equals("") ){
                disableInputs();
                refreshNotfs();
                Setup setup = new Setup();
                uiInstallNotf.textProperty().bind(setup.getStatusProp());
                setup.action(staticPrefixDirValue, new ActionCallback() {
                    @Override
                    public void success(String msg) {
                        enableInputs();
                    }
                    @Override
                    public void error(String msg) {
                        enableInputs();
                    }
                });
            }
        });

        uiStartDiagBtn.setOnMouseClicked( ev -> {
            String staticPrefixDirValue = uiStaticDirPrefixInput.getText();
            if( !staticPrefixDirValue.equals("") ){
                disableInputs();
                refreshNotfs();
                Diagnostic diagnostic = new Diagnostic();
                uiDiagNotf.textProperty().bind(diagnostic.getStatusProp());
                diagnostic.action(staticPrefixDirValue, new ActionCallback() {
                    @Override
                    public void success(String msg) {
                        enableInputs();
                    }
                    @Override
                    public void error(String msg) {
                        enableInputs();
                    }
                });
            }
        });

        uiUninstallBtn.setOnMouseClicked( ev -> {
            String staticPrefixDirValue = uiStaticDirPrefixInput.getText();
            if( !staticPrefixDirValue.equals("") ){
                disableInputs();
                refreshNotfs();
                Uninstall uninstall = new Uninstall();
                uiUninstallNotf.textProperty().bind(uninstall.getStatusProp());
                uninstall.action(staticPrefixDirValue, new ActionCallback() {
                    @Override
                    public void success(String msg) {
                        enableInputs();
                    }
                    @Override
                    public void error(String msg) {
                        enableInputs();
                    }
                });
            }
        });

    }

    private void refreshNotfs(){
        uiUninstallNotf.textProperty().unbind();
        uiUninstallNotf.setText("");

        uiInstallNotf.textProperty().unbind();
        uiInstallNotf.setText("");

        uiDiagNotf.textProperty().unbind();
        uiDiagNotf.setText("");
    }

    private void disableInputs(){
        uiInstallBtn.setDisable(true);
        uiStartDiagBtn.setDisable(true);
        uiUninstallBtn.setDisable(true);
        uiStaticDirPrefixInput.setDisable(true);
    }

    private void enableInputs(){
        uiInstallBtn.setDisable(false);
        uiStartDiagBtn.setDisable(false);
        uiUninstallBtn.setDisable(false);
        uiStaticDirPrefixInput.setDisable(false);
    }

}
