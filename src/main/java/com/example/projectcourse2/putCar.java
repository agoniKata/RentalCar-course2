package com.example.projectcourse2;

import com.example.projectcourse2.Data.DataControllerCar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class putCar {

    @FXML
    private TextField carBrande;

    @FXML
    private TextField carNumber;

    @FXML
    private ChoiceBox<String> classABC;

    @FXML
    private Hyperlink home;

    @FXML
    private Button putButton;

    private ObservableList<String> classOptions = FXCollections.observableArrayList(
            "Class A", "Class B", "Class C"
    );



    @FXML
    void initialize() {
        classABC.setItems(classOptions);

        putButton.setOnAction(event -> {
            try {

                writeCarData(carBrande.getText(), carNumber.getText(),classABC.getValue());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        But button = new But();
        home.setOnMouseClicked(actionEvent ->{
            button.HyperLink(home, "home.fxml");
        });
    }
    public void writeCarData(String brand,String number,String carClass) throws SQLException {
        DataControllerCar dataControllerCar = new DataControllerCar();
        Car car = new Car();
        car.setBrand(brand);
        car.setNumber(number);
        car.setCarClass(carClass);
        ResultSet result =dataControllerCar.getCar(car);

        int c = 0;

        while (result.next()  ) {
            c++;
        }

        if (c >= 1) {



        } else if (c==0 || !result.next()) {
            dataControllerCar.signUpUser(car);

        }
    }
}
