package com.example.demo;

public class UserData {
    private String name;
    private String cc;
    private String city;

    public UserData(){}

    public UserData(String name, String cc, String city) {
        this.name = name;
        this.cc = cc;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCc() {
        return cc;
    }

    public String getCity() {
        return city;
    }
}
