package com.example.bancodedados.domain;

public class User {

    private int _id;
    private String name;
    private String email;
    private String user;
    private String password;

    public User(int _id, String name, String email, String user, String password) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
