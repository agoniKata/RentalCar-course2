package com.example.projectcourse2.Data;

import com.example.projectcourse2.Car;
import com.example.projectcourse2.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.projectcourse2.Data.ConstCar.*;

public class DataControllerCar extends Config{
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(Car car) throws RuntimeException {
        String insert  = "INSERT INTO " + ConstCar.CAR_TABLE + " (" +
                ConstCar.CAR_BRAND + "," + ConstCar.CAR_NUMBER + "," +
                ConstCar.CAR_CLASS  + ")" + " VALUES (?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,car.getBrand());
            prSt.setString(2,car.getNumber());
            prSt.setString(3,car.getCarClass());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    public ResultSet getCar(Car car ){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + ConstCar.CAR_TABLE + " WHERE " +
                ConstCar.CAR_NUMBER + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,car.getNumber());



            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }
    public List<Car> getAllCar() throws SQLException, ClassNotFoundException {
        List<Car>cars =new ArrayList<>();
        String select = "SELECT * FROM " + ConstCar.CAR_TABLE;
        PreparedStatement prSt =getDbConnection().prepareStatement(select);
        ResultSet resultSet =prSt.executeQuery();
        while(resultSet.next()){
            String brand =resultSet.getString(CAR_BRAND);
            String number = resultSet.getString(CAR_NUMBER);
            String classCar = resultSet.getString(CAR_CLASS);
            Car car = new Car(brand,number,classCar);
            cars.add(car);
        }
        return cars;
    }

    public void deleteCarByNumber(String carNumber) throws RuntimeException {
        String delete = "DELETE FROM " + ConstCar.CAR_TABLE + " WHERE " +
                ConstCar.CAR_NUMBER + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.setString(1, carNumber);

            int rowsAffected = prSt.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
