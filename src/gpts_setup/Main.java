/* Gitaş - Obarey Inc 2018 */
package gpts_setup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ui.fxml"));
        Parent content = loader.load();
        primaryStage.setTitle("GPTS Setup");
        primaryStage.setScene(new Scene(content, 600, 400));
        primaryStage.getIcons().add(new Image(getClass().getResource("gpts_setup_ico.png").toExternalForm()));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
