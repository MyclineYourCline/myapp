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
            bill.setId(cursor.getInt(cursor.getColumnIndex("billID")));
            bill.setTotalMoney(cursor.getInt(cursor.getColumnIndex("totalMoney")));
            bill.setNote(cursor.getString(cursor.getColumnIndex("note")));
            bill.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
            bill.setOderID(cursor.getInt(cursor.getColumnIndex("oderID")));
            bill.setDate(cursor.getString(cursor.getColumnIndex("date")));
            list.add(bill);
        }
        return list;
    }
    public Long insertBill (Bill bill){
        ContentValues values = new ContentValues();
        values.put("date", bill.getDate());
        values.put("totalMoney", bill.getTotalMoney());
        values.put("note", bill.getNote());
        values.put("status", bill.getStatus());
        values.put("oderID", bill.getOderID());
        return db.insert("bill",null,values);
    }
    public int deleteBill (int id){
        return db.delete("bill","billID = ?", new String[]{String.valueOf(id)});
    }
    public List<Bill> getAll (){
      String sql = "SELECT * FROM bill";
      return get(sql);
    }
    public int updateBill (Bill bill){
        ContentValues values = new ContentValues();
        values.put("billID", bill.getId());
        values.put("date", bill.getDate());
        values.put("oderID", bill.getOderID());
        values.put("totalMoney", bill.getTotalMoney());
        values.put("note", bill.getNote());
        values.put("status", bill.getStatus());
        return db.update("Bill",values,"billID =? ", new String[]{String.valueOf(bill.getId())});
    }
}
