package com.example.ungdungchplay.ModelManager;

public class Oder {
    // "create table oder (" +
    //            "oderID INTEGER PRIMARY KEY AUTOINCREMENT ," +
    //            "serviceID INTEGER," +
    //            "quantity INTEGER," +
    //            "FOREIGN KEY (serviceID) REFERENCES service (serviceID))";;
    private int oderId, serviceID, quantity;

    public Oder(int oderId, int serviceID, int quantity) {
        this.oderId = oderId;
        this.serviceID = serviceID;
        this.quantity = quantity;
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
