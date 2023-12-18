package com.example.ungdungchplay.ModelManager;

public class DetailService {
    //  "create table serviceDetail (" +
    //            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "serviceID INTEGER," +
    //            "billID INTEGER," +
    //            "count INTEGER," +
    //            "price INTEGER," +
    //            "FOREIGN KEY (serviceID) REFERENCES service (id)," +
    //            "FOREIGN KEY (billID) REFERENCES bill (id))";
    private int id,serviceID,billID,count,price;

    public DetailService(int id, int serviceID, int billID, int count, int price) {
        this.id = id;
        this.serviceID = serviceID;
        this.billID = billID;
        this.count = count;
        this.price = price;
    }

    public DetailService() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
