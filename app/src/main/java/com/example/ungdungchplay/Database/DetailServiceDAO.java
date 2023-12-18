package com.example.ungdungchplay.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ungdungchplay.ModelManager.Bill;
import com.example.ungdungchplay.ModelManager.DetailService;

import java.util.ArrayList;
import java.util.List;

public class DetailServiceDAO {
    private SQLiteDatabase db;
    private SqlOpenHelper helper;

    public DetailServiceDAO(Context context) {
        helper = new SqlOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    // "create table serviceDetail (" +
    //            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "serviceID INTEGER," +
    //            "billID INTEGER," +
    //            "count INTEGER," +
    //            "price INTEGER," +
    //            "FOREIGN KEY (serviceID) REFERENCES service (id)," +
    //            "FOREIGN KEY (billID) REFERENCES bill (id))";
    @SuppressLint("Range")
    public List<DetailService> get (String sql, String...args){
        List<DetailService> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,args);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            DetailService detailService = new DetailService();
            detailService.setId(cursor.getInt(cursor.getColumnIndex("id")));
            detailService.setServiceID(cursor.getInt(cursor.getColumnIndex("serviceID")));
            detailService.setBillID(cursor.getInt(cursor.getColumnIndex("billID")));
            detailService.setCount(cursor.getInt(cursor.getColumnIndex("count")));
            detailService.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
            list.add(detailService);
        }
        return list;
    }
    public Long insertBill (DetailService detailService){
        ContentValues values = new ContentValues();
        values.put("id", detailService.getId());
        values.put("serviceID", detailService.getServiceID());
        values.put("billID", detailService.getBillID());
        values.put("count", detailService.getCount());
        values.put("price", detailService.getPrice());
        return db.insert("serviceDetail",null,values);
    }
    public int deleteBill (int id){
        return db.delete("serviceDetail","id = ?", new String[]{String.valueOf(id)});
    }
    public int updateBill (DetailService detailService){
        ContentValues values = new ContentValues();
        values.put("id", detailService.getId());
        values.put("serviceID", detailService.getServiceID());
        values.put("billID", detailService.getBillID());
        values.put("count", detailService.getCount());
        values.put("price", detailService.getPrice());
        return db.update("serviceDetail",values,"id =? ", new String[]{String.valueOf(detailService.getId())});
    }
}
