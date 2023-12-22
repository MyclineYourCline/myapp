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
    //    public static final String CREATE_TABLE_SERVICE = "" +
//            "create table service (" +
//            "serviceID INTEGER PRIMARY KEY AUTOINCREMENT ," +
//            "name TEXT," +
//            "type INTEGER," +
//            "count INTEGER," +
//            "price INTEGER," +
//            "image INTEGER," +
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
        while (cursor.moveToNext()){
            Service service = new Service();
            service.setId(cursor.getInt(cursor.getColumnIndex("serviceID")));
            service.setName(cursor.getString(cursor.getColumnIndex("name")));
            service.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
            service.setCount(cursor.getInt(cursor.getColumnIndex("count")));
            service.setType(cursor.getInt(cursor.getColumnIndex("type")));
            service.setImageURi(cursor.getString(cursor.getColumnIndex("imageUri")));
            service.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            list.add(service);
        }
        cursor.close();
        return list;
    }
    public int deleteService (int id){
        return db.delete("service","serviceID = ?", new String[]{String.valueOf(id)});
    }
    public int updateService(Service service){
        ContentValues values = new ContentValues();
        values.put("serviceID", service.getId());
        values.put("name",service.getName());
        values.put("price", service.getPrice());
        values.put("count", service.getCount());
        values.put("type", service.getType());
        values.put("imageUri", service.getImageURi());
        values.put("description", service.getDescription());
        return db.update("service",values,"serviceID = ?",
                new String[]{String.valueOf(service.getId())});
    }
    public long insertService (Service service){
        ContentValues values = new ContentValues();
        values.put("name",service.getName());
        values.put("price", service.getPrice());
        values.put("count", service.getCount());
        values.put("type", service.getType());
        values.put("imageUri", service.getImageURi());
        values.put("description", service.getDescription());
        return db.insert("service",null,values);
    }
    public List<Service> getAll (){
        String sql = "SELECT * FROM service";
        return get(sql);
    }
    public Service queryByName (String queryName){
        String sql = "SELECT * FROM service WHERE name = ?";
        List<Service> list = get(sql,queryName);
        if (list.size() == 0) return null;
        else  return  list.get(0);
    }
    public List<Service> queryByType (int type){
        String sql = "SELECT * FROM service WHERE type = ?";
        List<Service> list = get(sql, String.valueOf(type));
        if (list.size() == 0) return  null;
        else return list;
    }
}
