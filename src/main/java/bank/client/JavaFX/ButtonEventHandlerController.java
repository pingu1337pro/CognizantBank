package bank.client.JavaFX;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;


public class ButtonEventHandlerController {

    private final String darkThemeUrl = getClass().getResource("/cssFiles/darkApplication.css").toExternalForm();
    private final String lightThemeUrl = getClass().getResource("/cssFiles/lightApplication.css").toExternalForm();

    @FXML
    private ToggleButton themeButton;

    @FXML
    public void switchTheme() {
        ObservableList sceneStylesheet = themeButton.getParent().getStylesheets();

        if(sceneStylesheet.get(0).equals(darkThemeUrl)) {
            sceneStylesheet.remove(darkThemeUrl);
            if (!sceneStylesheet.contains(lightThemeUrl)) sceneStylesheet.add(lightThemeUrl);
        } else if(sceneStylesheet.get(0).equals(lightThemeUrl)) {
            sceneStylesheet.remove(lightThemeUrl);
            if (!sceneStylesheet.contains(darkThemeUrl)) sceneStylesheet.add(darkThemeUrl);
        }
    }

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private void initialize() {
        loginButton.setText("Login");
        signUpButton.setText("Sign Up");
    }
}