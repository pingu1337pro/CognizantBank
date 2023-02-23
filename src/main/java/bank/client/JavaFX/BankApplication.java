package bank.client.JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;


public class BankApplication extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent startMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlFiles/initialMenu.fxml")));
        Scene scene = new Scene(startMenu);

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/bankIcon.png")));
        stage.getIcons().add(icon);
        stage.setTitle("Kessler Financial Ltd.");
        stage.setScene(scene);
        stage.show();
    }
}