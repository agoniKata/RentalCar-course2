package com.example.projectcourse2;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.projectcourse2.Data.DataControllerCarToUser;
import com.example.projectcourse2.Data.DataControllerUser;
import com.example.projectcourse2.Shake.Shake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Register {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink home;

    @FXML
    private TextField name;

    @FXML
    private TextField number;

    @FXML
    private Button signupButton;

    @FXML
    private TextField surname;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;
    private ObservableList<String> userList = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        But button = new But();
        home.setOnMouseClicked(actionEvent ->{
            button.HyperLink(home, "home.fxml");
        });
        signupButton.setOnAction(actionEvent -> {
            String loginText = userName.getText().trim();
            String loginPassword =userPassword.getText().trim();
            if(!loginText.equals("") && !loginPassword.equals("")){
                writeData(userName.getText(),userPassword.getText());
            }
        });
    }
    private void writeData(String loginText, String loginPassword) {


        DataControllerUser dbHandler = new DataControllerUser();
        User users = new User();
        users.setLogin(loginText);
        users.setPassword(loginPassword);

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
            Shake loginH = new Shake(userName);
            Shake pas = new Shake(userPassword);
            Shake name1 = new Shake(name);
            Shake  surname1 = new Shake(surname);
            Shake number1 = new Shake(number);

            loginH.playAnim();
            name1.playAnim();
            surname1.playAnim();
            number1.playAnim();
            pas.playAnim();

        }


        else{
            DataControllerUser databaseHandler = new DataControllerUser();

            String name1 = name.getText();
            String surname1 = surname.getText();
            String username = userName.getText();
            String password = userPassword.getText();
            String balance =number.getText();


            User user = new User(name1,surname1,balance,username,password);



            databaseHandler.signUpUser(user);


        }

    }
}
