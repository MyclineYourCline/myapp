package com.example.ungdungchplay.ModelManager;

public class Customer {
//  "create table customer (" +
//            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//            "name TEXT," +
//            "phone TEXT," +
//            "address TEXT," +
//            "birth DATE)";
    private int id;
    private String name,phone,address,birth;

    public Customer() {
    }

    public Customer(int id, String name, String phone, String address, String birth) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
