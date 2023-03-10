package bank.client.JavaFX;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class loginMenu {

    private Node node;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Parent root;

    private final String darkThemeUrl = Objects.requireNonNull(getClass().getResource("/cssFiles/darkApplication.css")).toExternalForm();
    private final String lightThemeUrl = Objects.requireNonNull(getClass().getResource("/cssFiles/lightApplication.css")).toExternalForm();

    @FXML
    public Label title = new Label();

    @FXML
    private final Button backButton = new Button();

    @FXML
    public void switchToPreviousMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlFiles/initialMenu.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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
    private final TextField email = new TextField();

    @FXML
    private final PasswordField password = new PasswordField();

    @FXML
    public Button submitButton = new Button();

    @FXML
    public void logIn() {
        String email = this.email.getText();
        String password = this.password.getText();

        //TODO: edit if statement => search account for email and control passwort
        if (email != null && password != null) {
            this.email.clear();
            this.password.clear();

            System.out.println("Richtig");
        } else {
            this.password.clear();

            System.out.println("Falsch");
            //TODO: "Wrong password, try again!"-Field appears
        }
    }
}