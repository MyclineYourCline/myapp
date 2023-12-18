package com.example.ungdungchplay.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ungdungchplay.ModelManager.Bill;
import com.example.ungdungchplay.ModelManager.DetailBill;

import java.util.ArrayList;
import java.util.List;

public class BillDetailDAO {
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
    private SQLiteDatabase db;
    private SqlOpenHelper helper;

    public BillDetailDAO(Context context) {
        helper = new SqlOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<DetailBill> get (String sql, String...args){
        List<DetailBill> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,args);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
           DetailBill detailBill = new DetailBill();
            detailBill.setId(cursor.getInt(cursor.getColumnIndex("id")));
            detailBill.setBillID(cursor.getInt(cursor.getColumnIndex("billID")));
            detailBill.setUserID(cursor.getInt(cursor.getColumnIndex("userID")));
            detailBill.setTableID(cursor.getInt(cursor.getColumnIndex("tableID")));
            detailBill.setCustomerID(cursor.getInt(cursor.getColumnIndex("customerID")));
            detailBill.setDate(cursor.getString(cursor.getColumnIndex("date")));
        }
        return list;
    }
    public Long insertBill (DetailBill detailBill){
        ContentValues values = new ContentValues();
        values.put("id", detailBill.getId());
        values.put("billID", detailBill.getBillID());
        values.put("userID", detailBill.getUserID());
        values.put("tableID", detailBill.getTableID());
        values.put("customerID", detailBill.getCustomerID());
        values.put("date", detailBill.getDate());
        return db.insert("billDetail",null,values);
    }
    public int deleteBill (int id){
        return db.delete("billDetail","id = ?", new String[]{String.valueOf(id)});
    }
    public int updateBill (DetailBill detailBill){
        ContentValues values = new ContentValues();
        values.put("id", detailBill.getId());
        values.put("billID", detailBill.getBillID());
        values.put("userID", detailBill.getUserID());
        values.put("tableID", detailBill.getTableID());
        values.put("customerID", detailBill.getCustomerID());
        values.put("date", detailBill.getDate());
        return db.update("billDetail",values,"id =? ", new String[]{String.valueOf(detailBill.getId())});
    }

}
