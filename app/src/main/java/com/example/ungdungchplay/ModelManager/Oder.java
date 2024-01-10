package com.example.ungdungchplay.ModelManager;

public class Oder {
    // "create table oder (" +
    //            "oderID INTEGER PRIMARY KEY AUTOINCREMENT ," +
    //            "serviceID INTEGER," +
    //            "quantity INTEGER," +
    //            "FOREIGN KEY (serviceID) REFERENCES service (serviceID))";;
    private int oderId, serviceID, quantity,status;
    private String tableID;

    public Oder(int oderId, int serviceID, int quantity, int status, String tableID) {
        this.oderId = oderId;
        this.serviceID = serviceID;
        this.quantity = quantity;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }
    public int getTotalMoney(Service service){
        return quantity * service.getPrice();
    }

    @Override
    public String toString() {
        return "Oder{" +
                "oderId=" + oderId +
                ", serviceID=" + serviceID +
                ", quantity=" + quantity +
                ", status=" + status +
                ", tableID='" + tableID + '\'' +
                '}';
    }
}
