package com.example.ungdungchplay.Database;

public class DbStruct {
    public static final int INSERT_ERR = -1;
    public static final int DELETE_ERR = 0;
    public static final int UPDATE_ERR = 0;
    public static final int ERR = 0;
    public static final int ITEM_CLICK = 0;
    public static final int ITEM_LONG_CLICK = 1;
    public static final String MESSAGE_ERR = "Data error";


    public static final String CREATE_TABLE_USER = "" +
            "create table user (" +
            "userID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT," +
            "address TEXT," +
            "phone TEXT," +
            "birth DATE," +
            "password TEXT," +
            "avatar INTEGER)";
    public static final String CREATE_TABLE_ROOM = "" +
            "create table room (" +
            "roomID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT)";
    public static final String CREATE_TABLE_TABLE = "" +
            "create table tableRoom (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "roomID INTEGER," +
            "name TEXT," +
            "status INTEGER," +
            "FOREIGN KEY (roomID) REFERENCES room (roomID))";
    public static final String CREATE_TABLE_BILL ="" +
            "create table bill (" +
            "billID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "customerID INTEGER," +
            "totalMoney INTEGER," +
            "note TEXT," +
            "status INTEGER," +
            "FOREIGN KEY (customerID) REFERENCES customer (customerID))";
    public static final String CREATE_TABLE_CUSTOMER = "" +
            "create table customer (" +
            "customerID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT," +
            "phone TEXT," +
            "address TEXT," +
            "birth DATE)";
    public static final String CREATE_TABLE_SERVICE = "" +
            "create table service (" +
            "serviceID INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "name TEXT," +
            "price INTEGER," +
            "description TEXT)";
    public static final String CREATE_TABLE_detailSERVICE = "" +
            "create table serviceDetail (" +
            "serviceDetailID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "serviceID INTEGER," +
            "billID INTEGER," +
            "count INTEGER," +
            "price INTEGER," +
            "FOREIGN KEY (serviceID) REFERENCES service (serviceID)," +
            "FOREIGN KEY (billID) REFERENCES bill (billID))";
    public static final String CREATE_TABLE_BILLDetail = "" +
            "create table billDetail (" +
            "billDetailID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "billID INTEGER," +
            "userID INTEGER," +
            "tableID INTEGER," +
            "customerID INTEGER," +
            "date DATE," +
            "FOREIGN KEY (billID) REFERENCES bill (billID)," +
            "FOREIGN KEY (userID) REFERENCES user (userID)," +
            "FOREIGN KEY (tableID) REFERENCES tableRoom (tableID)," +
            "FOREIGN KEY (customerID) REFERENCES customer (customerID))";
    public static final String DROP_TABLE_BILLDetail = "" +
            "DROP TABLE IF EXISTS billDetail";
    public static final String DROP_TABLE_detailSERVICE = "" +
            "DROP TABLE IF EXISTS serviceDetail";
    public static final String DROP_TABLE_USER = "" +
            "DROP TABLE IF EXISTS user";
    public static final String DROP_TABLE_ROOM = "" +
            "DROP TABLE IF EXISTS room";
    public static final String DROP_TABLE_TABLE = "" +
            "DROP TABLE IF EXISTS table";
    public static final String DROP_TABLE_BILL = "" +
            "DROP TABLE IF EXISTS bill";
    public static final String DROP_TABLE_CUSTOMER = "" +
            "DROP TABLE IF EXISTS customer";
    public static final String DROP_TABLE_SERVICE = "" +
            "DROP TABLE IF EXISTS service";
}
