package bank.client.JavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;


public class BankApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent startMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlDreckBullshitKacke/initialMenu.fxml")));
        Scene scene = new Scene(startMenu);

        ToggleButton themeButton = new ToggleButton();


        Button signUpButton = new Button();

        Image icon = new Image(getClass().getResourceAsStream("/icons/bankIcon.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Kessler Financial Ltd");
        stage.setScene(scene);
        stage.show();
    }

    public void setTheme(/*currentTheme*/) {
        if(true/*currentTheme == darkTheme*/) {
            /*this.theme = lightTheme;*/
        } else {
            /*this.theme = darkTheme*/
        }
    }
}