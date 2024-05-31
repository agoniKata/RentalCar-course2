package com.example.projectcourse2;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.example.projectcourse2.Shake.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private PasswordField adminPassword;

    @FXML
    private Hyperlink home;
    @FXML
    private TextField adminName;

    @FXML
    private Button loginButton;

    @FXML
    void initialize() {
        HashMap<String,String> admin = new HashMap<>();
        admin.put("a", "1");
        admin.put("admin", "4567");

        loginButton.setOnAction(actionEvent -> {
            String enteredLogin = adminName.getText();
            String enteredPassword = adminPassword.getText();
            if (admin.containsKey(enteredLogin) && admin.get(enteredLogin).equals(enteredPassword)) {

                loginButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("putcar.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();}
            else{
                Shake loginShake = new Shake(adminName);
                Shake passwordShake = new Shake(adminPassword);
                loginShake.playAnim();
                passwordShake.playAnim();
            }
        });
        But button = new But();
        home.setOnMouseClicked(actionEvent ->{
            button.HyperLink(home, "home.fxml");
        });
    }

}
