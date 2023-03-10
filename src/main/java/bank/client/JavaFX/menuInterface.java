package bank.client.JavaFX;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

import java.util.Objects;

public interface menuInterface {
    String darkThemeUrl = Objects.requireNonNull(menuInterface.class.getResource("/cssFiles/darkApplication.css")).toExternalForm();
    String lightThemeUrl = Objects.requireNonNull(menuInterface.class.getResource("/cssFiles/lightApplication.css")).toExternalForm();

    //@FXML
    //ToggleButton themeButton;
}
