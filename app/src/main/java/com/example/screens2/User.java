package com.example.screens2;

class User {
    int _id;
    String username;
    String password;
    String phone_number;

    public int get_id() {
        return _id;
    }
    public String getPassword() {
        return password;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public String getUsername() {
        return username;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
