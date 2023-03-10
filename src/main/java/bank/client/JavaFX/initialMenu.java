package bank.client.JavaFX;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;


public class initialMenu {

    private Node node;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Parent root;

    private final String darkThemeUrl = Objects.requireNonNull(getClass().getResource("/cssFiles/darkApplication.css")).toExternalForm();
    private final String lightThemeUrl = Objects.requireNonNull(getClass().getResource("/cssFiles/lightApplication.css")).toExternalForm();

    @FXML
    private ToggleButton themeButton = new ToggleButton();

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
    public Button loginButton = new Button();

    public void switchToLoginMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlFiles/loginMenu.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public Button signUpButton = new Button();

    public void switchToSignUpMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlFiles/signUpMenu.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}