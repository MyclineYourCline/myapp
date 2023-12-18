package com.example.ungdungchplay.ModelManager;

public class User {
    //name
    //id
    //adr
    //avatar
    //numberp
    //birth
    private int id, avatar;
    private String name, address, phone, birth;

    public User() {
    }

    public User(int id, int avatar, String name, String address, String phone, String birth) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
