package com.example.karthickramjee.androidlab;

/**
 * Created by karthickramjee on 15/10/16.
 */

public class SignData {
    String name,email,password;
    SignData()
    {

    }
    SignData(String name,String email,String password)
    {
        super();
        this.name=name;
        this.email=email;
        this.password=password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
