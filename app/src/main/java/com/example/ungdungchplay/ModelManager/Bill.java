package com.example.ungdungchplay.ModelManager;

public class Bill {
    // "create table bill (" +
    //            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "customerID INTEGER," +
    //            "totalMoney INTEGER," +
    //            "note TEXT," +
    //            "status INTEGER," +
    //            "FOREIGN KEY (customerID) REFERENCES customer (id))";
    private int id,customerID,totalMoney,status, oderID;
    private String note,tableID;

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

    public int getOderID() {
        return oderID;
    }

    public void setOderID(int oderID) {
        this.oderID = oderID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public Bill(int id, int customerID, int totalMoney, int status, int oderID, String note, String tableID) {
        this.id = id;
        this.customerID = customerID;
        this.totalMoney = totalMoney;
        this.status = status;
        this.oderID = oderID;
        this.note = note;
        this.tableID = tableID;
    }
}
