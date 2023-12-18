package com.example.ungdungchplay.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ungdungchplay.ModelManager.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    //CREATE_TABLE_BILL ="" +
    //            "create table bill (" +
    //            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "customerID INTEGER," +
    //            "totalMoney INTEGER," +
    //            "note TEXT," +
    //            "status INTEGER," +
    //            "FOREIGN KEY (customerID) REFERENCES customer (id))";
    private SQLiteDatabase db;
    private SqlOpenHelper helper;

    public BillDAO(Context context) {
       helper = new SqlOpenHelper(context);
       db = helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<Bill> get (String sql, String...args){
        List<Bill> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,args);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Bill bill = new Bill();
            bill.setId(cursor.getInt(cursor.getColumnIndex("id")));
            bill.setCustomerID(cursor.getInt(cursor.getColumnIndex("customerID")));
            bill.setTotalMoney(cursor.getInt(cursor.getColumnIndex("totalMoney")));
            bill.setNote(cursor.getString(cursor.getColumnIndex("note")));
            bill.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
            list.add(bill);
        }
        return list;
    }
    public Long insertBill (Bill bill){
        ContentValues values = new ContentValues();
        values.put("id", bill.getId());
        values.put("customerID", bill.getCustomerID());
        values.put("totalMoney", bill.getTotalMoney());
        values.put("note", bill.getNote());
        values.put("status", bill.getStatus());
        return db.insert("bill",null,values);
    }
    public int deleteBill (int id){
        return db.delete("bill","id = ?", new String[]{String.valueOf(id)});
    }
    public int updateBill (Bill bill){
        ContentValues values = new ContentValues();
        values.put("id", bill.getId());
        values.put("customerID", bill.getCustomerID());
        values.put("totalMoney", bill.getTotalMoney());
        values.put("note", bill.getNote());
        values.put("status", bill.getStatus());
        return db.update("Bill",values,"id =? ", new String[]{String.valueOf(bill.getId())});
    }
}
