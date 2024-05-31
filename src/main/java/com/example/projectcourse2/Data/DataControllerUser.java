package com.example.projectcourse2.Data;

import com.example.projectcourse2.User;

import java.sql.*;

public class DataControllerUser extends Config{
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    public void signUpUser(User user) throws RuntimeException {
        String insert  = "INSERT INTO " + Const.USER_TABLE + " (" +
                Const.USERS_NAME + "," + Const.USERS_SURNAME + "," +
                Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + "," +
                Const.USERS_MONEY  + ")" + " VALUES (?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,user.getName());
            prSt.setString(2,user.getSurname());
            prSt.setString(3,user.getLogin());
            prSt.setString(4,user.getPassword());
            prSt.setString(5, String.valueOf(user.getMoney()));



            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getUser(User user ){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getLogin());
            prSt.setString(2,user.getPassword());


            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }

    public boolean updateAndCheckUserMoney(User user, double newMoney) throws RuntimeException {
        String updateMoney = "UPDATE " + Const.USER_TABLE + " SET " + Const.USERS_MONEY + "=? WHERE " + Const.USERS_USERNAME + "=?";

        try (PreparedStatement updateStatement = getDbConnection().prepareStatement(updateMoney)) {
            dbConnection.setAutoCommit(false);

            updateStatement.setDouble(1, newMoney);
            updateStatement.setString(2, user.getLogin());

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                if (newMoney >= 0) {
                    dbConnection.commit();
                    return true;
                } else {

                    dbConnection.rollback();
                    return false;
                }
            } else {
                dbConnection.rollback();
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);
        } finally {
            try {

                dbConnection.setAutoCommit(true);
            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }
    }

}
