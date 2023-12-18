package com.example.ungdungchplay.ModelManager;

public class Bill {
    // "create table bill (" +
    //            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "customerID INTEGER," +
    //            "totalMoney INTEGER," +
    //            "note TEXT," +
    //            "status INTEGER," +
    //            "FOREIGN KEY (customerID) REFERENCES customer (id))";
    private int id,customerID,totalMoney,status;
    private String note;

    public Bill(int id, int customerID, int totalMoney, int status, String note) {
        this.id = id;
        this.customerID = customerID;
        this.totalMoney = totalMoney;
        this.status = status;
        this.note = note;
    }

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
