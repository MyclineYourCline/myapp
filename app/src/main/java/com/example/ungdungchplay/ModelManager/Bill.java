package com.example.ungdungchplay.ModelManager;

public class Bill {
   // "create table bill (" +
    //            "billID INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "totalMoney INTEGER," +
    //            "note TEXT," +
    //            "status INTEGER," +
    //            "oderID INTEGER," +
    //            "FOREIGN KEY (oderID) REFERENCES oder (oderID))";
    private int id,totalMoney,status,oderID;
    private String note,date;

    public Bill() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bill(int id, int totalMoney, int status, int oderID, String note, String date) {
        this.id = id;
        this.totalMoney = totalMoney;
        this.status = status;
        this.oderID = oderID;
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
