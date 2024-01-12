package com.example.ungdungchplay.Database;

public class DbStruct {
    public static final int INSERT_ERR = -1;
    public static final int DELETE_ERR = 0;
    public static final int UPDATE_ERR = 0;
    public static final int ERR = 0;
    public static final int ITEM_CLICK = 0;
    public static final int ITEM_LONG_CLICK = 1;
    public static final int BILL_STATUS_ISPAYMENT = 1;
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
            "create table tableRoom (tableID TEXT PRIMARY KEY," +
            "roomID INTEGER," +
            "name TEXT," +
            "status INTEGER," +
            "FOREIGN KEY (roomID) REFERENCES room (roomID))";
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
            "type INTEGER," +
            "count INTEGER," +
            "price INTEGER," +
            "imageUri TEXT," +
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
    public static final String CREATE_TABLE_ODER = "" +
            "create table oder (" +
            "oderID INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "serviceID INTEGER," +
            "quantity INTEGER," +
            "tableID TEXT," +
            "billID INTEGER," +
            "status INTEGER," +
            "FOREIGN KEY (tableID) REFERENCES tableRoom (tableID),"+
            "FOREIGN KEY (billID) REFERENCES bill (billID),"+
            "FOREIGN KEY (serviceID) REFERENCES service (serviceID))";

    public static final String CREATE_TABLE_BILL ="" +
            "create table bill (" +
            "billID TEXT PRIMARY KEY," +
            "totalMoney INTEGER," +
            "note TEXT," +
            "status INTEGER," +
            "oderID INTEGER," +
            "date TEXT)";
    public static final String DROP_TABLE_BILLDetail = "" +
            "DROP TABLE IF EXISTS billDetail";
    public static final String DROP_TABLE_detailSERVICE = "" +
            "DROP TABLE IF EXISTS serviceDetail";
    public static final String DROP_TABLE_USER = "" +
            "DROP TABLE IF EXISTS user";
    public static final String DROP_TABLE_ROOM = "" +
            "DROP TABLE IF EXISTS room";
    public static final String DROP_TABLE_TABLE = "" +
            "DROP TABLE IF EXISTS tableRoom";
    public static final String DROP_TABLE_BILL = "" +
            "DROP TABLE IF EXISTS bill";
    public static final String DROP_TABLE_CUSTOMER = "" +
            "DROP TABLE IF EXISTS customer";
    public static final String DROP_TABLE_SERVICE = "" +
            "DROP TABLE IF EXISTS service";
    public static final String DROP_TABLE_ODER = "" +
            "DROP TABLE IF EXISTS oder";
}
