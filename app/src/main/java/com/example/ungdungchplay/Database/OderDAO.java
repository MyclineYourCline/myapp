package com.example.ungdungchplay.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ungdungchplay.ModelManager.Oder;

import java.util.ArrayList;
import java.util.List;

public class OderDAO {
    private SqlOpenHelper helper;
    private SQLiteDatabase database;

    public OderDAO(Context context) {
       helper = new SqlOpenHelper(context);
       database = helper.getWritableDatabase();
    }
    //"create table oder (" +
    //            "oderID INTEGER PRIMARY KEY AUTOINCREMENT ," +
    //            "serviceID INTEGER," +
    //            "quantity INTEGER," +
    //            "FOREIGN KEY (serviceID) REFERENCES service (serviceID))";;
    // "FOREIGN KEY (tableID) REFERENCES tableRoom (TableID))";;
    @SuppressLint("Range")
    private List<Oder> get (String sql, String...args){
        List<Oder> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql,args);
        while (cursor.moveToNext()){
            Oder oder = new Oder();
            oder.setTableID(cursor.getString(cursor.getColumnIndex("tableID")));
            oder.setOderId(cursor.getInt(cursor.getColumnIndex("oderID")));
            oder.setServiceID(cursor.getInt(cursor.getColumnIndex("serviceID")));
            oder.setQuantity(cursor.getInt(cursor.getColumnIndex("quantity")));
            list.add(oder);
        }
        return list;
    }
    public long insertOder (Oder oder){
        ContentValues values = new ContentValues();
        values.put("serviceID",oder.getServiceID());
        values.put("quantity",oder.getQuantity());
        values.put("tableID",oder.getTableID());
        return database.insert("oder", null,values);
    }
    public List<Oder> getByTableID (String tableID){
        String sql = "SELECT * FROM oder WHERE tableID = ?";
        return get(sql,tableID);
    }
}
