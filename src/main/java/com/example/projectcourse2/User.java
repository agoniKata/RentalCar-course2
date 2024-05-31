package com.example.projectcourse2;

public class User {
    private String name;
    private String surname;
    private String money;
    private String login;
    private String password;

    public User(String name, String surname, String money, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.money = money;
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
