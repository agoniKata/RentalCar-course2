package com.example.projectcourse2;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.projectcourse2.Data.DataControllerCarToUser;
import com.example.projectcourse2.Data.DataControllerUser;
import com.example.projectcourse2.Shake.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Home {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Home;

    @FXML
    private Hyperlink adminButton;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink signButton;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;


    @FXML
    void initialize() {
        But button = new But();
        signButton.setOnMouseClicked(actionEvent ->{
           button.HyperLink(signButton, "register.fxml");
        });

        loginButton.setOnAction(actionEvent -> {
            String loginText = userName.getText().trim();
            String loginPassword =userPassword.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText,loginPassword);




            }
            else{ Shake userLoginAnim = new Shake(userName);
                Shake userPasswordAnim = new Shake(userPassword);
                userLoginAnim.playAnim();
                userPasswordAnim.playAnim();}
        });

        adminButton.setOnMouseClicked(actionEvent ->{
        button.HyperLink(adminButton,"admin.fxml");});
    }
    private void loginUser(String loginText, String loginPassword) {


        DataControllerUser dbHandler = new DataControllerUser();

        User users = new User();
        users.setLogin(loginText);
        users.setPassword(loginPassword);

        Global global = new Global(loginText,loginPassword);

        ResultSet result = dbHandler.getUser(users);

        int c = 0;

        while (true){
            try {
                if (!result.next())

                    break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            c++;
        }
        if(c >=1){
            loginButton.setOnAction(event -> {
                loginButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("mainController.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.showAndWait();
            });

        }
        else{ Shake userLoginAnim = new Shake(userName);
            Shake userPasswordAnim = new Shake(userPassword);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();}

    }
}
