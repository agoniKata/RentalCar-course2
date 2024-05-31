package com.example.projectcourse2;

import com.example.projectcourse2.Data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MainController {

    @FXML
    private TableView<Car> carTableView;

    @FXML
    private TableView<CarToUser> carToUser;

    @FXML
    private TableColumn<Car, String> brandColumn;

    @FXML
    private TableColumn<Car, String> numberColumn;

    @FXML
    private TableColumn<Car, String> classColumn;

    @FXML
    private TableColumn<Car, String> nameUserT;

    @FXML
    private TableColumn<Car, String> carBrandUser;
    @FXML
    private TextField Money;

    @FXML
    private Hyperlink home;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private Button rentButton;

    @FXML
    private Button returnButton;

    @FXML
    private Text refrashText;

    private final DataControllerCar dataControllerCar = new DataControllerCar();
    private final DataControllerCarToUser dataControllerCarToUser = new DataControllerCarToUser();
    private final DataControllerUser dataControllerUser = new DataControllerUser();
    But but = new But();

    String nameUser = null;
    Double moneyUser;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        home.setOnMouseClicked(mouseEvent -> {
            but.HyperLink(home,"home.fxml");
        });

        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("carClass"));

        nameUserT.setCellValueFactory(new PropertyValueFactory<>("name"));
        carBrandUser.setCellValueFactory(new PropertyValueFactory<>("brand"));


        User user = new User();
        user.setLogin(Global.loginText);
        user.setPassword(Global.loginPassword);
        ResultSet resultSet = dataControllerUser.getUser(user);


        while (resultSet.next()){
            moneyUser = Double.valueOf(resultSet.getString("money"));
            nameUser = resultSet.getString("name");

            name.setText(resultSet.getString("name"));
            surname.setText(resultSet.getString("surname"));
            Money.setText(resultSet.getString("money"));
        }
        loadData();

        String finalNameUser = nameUser;
        carTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                rentButton.setOnAction(actionEvent -> {
                    dataControllerCar.deleteCarByNumber(newValue.getNumber());

                    dataControllerCarToUser.putCarToUser( finalNameUser,newValue.getBrand());

                    dataControllerUser.updateAndCheckUserMoney(user,calculatePrice(newValue.getCarClass()));
                });
            }
        });

        carToUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                returnButton.setOnAction(actionEvent -> {
                    dataControllerCarToUser.deleteCarToUserByBrand(newValue.brand);
                });
            }
        });

        refrashText.setOnMouseClicked(mouseEvent -> {
            loadData();


        });
        }
    private void loadData() {
        try {
            List<Car> cars = dataControllerCar.getAllCar();

            ObservableList<Car> carList = FXCollections.observableArrayList(cars);

            carTableView.setItems(carList);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            List<CarToUser> carToUsers = dataControllerCarToUser.getCarToUserByName(nameUser);
            ObservableList<CarToUser> carToUserList = FXCollections.observableArrayList(carToUsers);
            carToUser.setItems(carToUserList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public double calculatePrice(String classCar){
        double price = 0;
        switch (classCar){
            case "Class A":
                price = 10000;
                break;
            case "Class B": price = 15000;
                break;
            case "Class C": price = 20000;
                break;
        }
       return price;

    }

    }

