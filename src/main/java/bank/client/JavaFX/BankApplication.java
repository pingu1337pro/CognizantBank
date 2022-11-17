package bank.client.JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Objects;


public class BankApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlDreckBullshitKacke/initialMenu.fxml")));
        Scene scene = new Scene(root);
        Image icon = new Image(getClass().getResourceAsStream("/icons/bankIcon.png"));

        stage.setTitle("Kessler Financial Ltd");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}