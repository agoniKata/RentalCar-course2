package com.example.projectcourse2.Data;

import com.example.projectcourse2.Car;
import com.example.projectcourse2.CarToUser;
import com.example.projectcourse2.Global;
import com.example.projectcourse2.Data.ConstCarToUser.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.logging.Logger.global;

public class DataControllerCarToUser extends  Config{
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    public void putCarToUser(String name,String brand){
        String insert = "INSERT INTO " + ConstCarToUser.CARt_TABLE + " (" +
                ConstCarToUser.CARt_NAME + "," + ConstCarToUser.CARt_BRAND + ")" + " VALUES (?,?)";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,name);
            prSt.setString(2,brand);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CarToUser> getCarToUserByName(String userName) throws SQLException, ClassNotFoundException {
        List<CarToUser> cars = new ArrayList<>();
        String select = "SELECT * FROM " + ConstCarToUser.CARt_TABLE + " WHERE " + ConstCarToUser.CARt_NAME + "=?";

        try (PreparedStatement prSt = getDbConnection().prepareStatement(select)) {
            prSt.setString(1, userName);
            ResultSet resultSet = prSt.executeQuery();

            while (resultSet.next()) {
                String brand = resultSet.getString(ConstCarToUser.CARt_BRAND);
                String name = resultSet.getString(ConstCarToUser.CARt_NAME);
                CarToUser carToUser = new CarToUser(name, brand);
                cars.add(carToUser);
            }
        }

        return cars;
    }

    public void deleteCarToUserByBrand(String brand) throws RuntimeException {
        String delete = "DELETE FROM " + ConstCarToUser.CARt_TABLE + " WHERE " +
                ConstCarToUser.CARt_BRAND + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.setString(1, brand);

            int rowsAffected = prSt.executeUpdate();



        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

