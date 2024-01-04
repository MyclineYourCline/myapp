package com.example.ungdungchplay.ModelManager;

public class Oder {
    //  "create table oder (" +
    //            "oderID INTEGER PRIMARY KEY AUTOINCREMENT ," +
    //            "serviceID INTEGER," +
    //            "quantity INTEGER," +
    //            "tableID TEXT," +
    //            "FOREIGN KEY (tableID) REFERENCES tableRoom (tableID)," +
    //            "FOREIGN KEY (serviceID) REFERENCES service (serviceID))";;
    private int oderId, serviceID, quantity;
    private String tableID;

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public Oder(int oderId, int serviceID, int quantity, String tableID) {
        this.oderId = oderId;
        this.serviceID = serviceID;
        this.quantity = quantity;
        this.tableID = tableID;
    }

    public Oder() {
    }

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
