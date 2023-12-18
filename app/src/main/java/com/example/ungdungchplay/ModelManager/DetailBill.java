package com.example.ungdungchplay.ModelManager;

public class DetailBill {
    //"create table billDetail (" +
    //            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "billID INTEGER," +
    //            "userID INTEGER," +
    //            "tableID INTEGER," +
    //            "customerID INTEGER," +
    //            "date DATE," +
    //            "FOREIGN KEY (billID) REFERENCES bill (id)," +
    //            "FOREIGN KEY (userID) REFERENCES user (id)," +
    //            "FOREIGN KEY (tableID) REFERENCES table (id)," +
    //            "FOREIGN KEY (customerID) REFERENCES customer (id),)";
    private int id, billID,userID,tableID,customerID;
    private String date;

    public DetailBill(int id, int billID, int userID, int tableID, int customerID, String date) {
        this.id = id;
        this.billID = billID;
        this.userID = userID;
        this.tableID = tableID;
        this.customerID = customerID;
        this.date = date;
    }

    public DetailBill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
