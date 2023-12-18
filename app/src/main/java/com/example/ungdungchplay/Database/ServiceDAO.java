package com.example.ungdungchplay.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.ParcelUuid;

import com.example.ungdungchplay.ModelManager.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    //  "create table service (" +
    //            "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
    //            "name TEXT," +
    //            "price INTEGER," +
    //            "description TEXT)";
    private SqlOpenHelper helper;
    private SQLiteDatabase db;

    public ServiceDAO(Context context) {
        helper = new SqlOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    private List<Service> get(String sql, String...args){
        List<Service> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,args);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Service service = new Service();
            service.setId(cursor.getInt(cursor.getColumnIndex("id")));
            service.setName(cursor.getString(cursor.getColumnIndex("name")));
            service.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
            service.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            list.add(service);
        }
        return list;
    }
    public int deleteService (int id){
        return db.delete("service","id = ?", new String[]{String.valueOf(id)});
    }
    public int updateService(Service service){
        ContentValues values = new ContentValues();
        values.put("id", service.getId());
        values.put("name",service.getName());
        values.put("price", service.getPrice());
        values.put("description", service.getDescription());
        return db.update("service",values,"id = ?",
                new String[]{String.valueOf(service.getId())});
    }
    public long insertService (Service service){
        ContentValues values = new ContentValues();
        values.put("id", service.getId());
        values.put("name",service.getName());
        values.put("price", service.getPrice());
        values.put("description", service.getDescription());
        return db.insert("service",null,values);
    }
    public List<Service> getAll (){
        String sql = "SELECT * FROM service";
        return get(sql);
    }
}
