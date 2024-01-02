package com.example.ungdungchplay.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ungdungchplay.InterfaceManager.OderFragmentInterFace;
import com.example.ungdungchplay.InterfaceManager.SendData.OnDataLoadedListener;
import com.example.ungdungchplay.ModelManager.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    //
    @SuppressLint("Range")
    private CompletableFuture<List<Service>> getAsync(String sql, String... args) {
        CompletableFuture<List<Service>> future = new CompletableFuture<>();
        ExecutorService serviceLoadData = Executors.newSingleThreadExecutor();
        serviceLoadData.execute(() -> {
            List<Service> list = new ArrayList<>();
            Cursor cursor = db.rawQuery(sql, args);
            while (cursor.moveToNext()) {
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
            future.complete(list); // Thông báo rằng công việc đã hoàn thành và truyền danh sách dữ liệu
            serviceLoadData.shutdown();
        });

        return future;
    }
    // thu nghiem
    @SuppressLint("Range")
    public void getAsync2(String sql,OnDataLoadedListener onDataLoadedListener, String...args) {
        ExecutorService serviceLoadData = Executors.newSingleThreadExecutor();
        serviceLoadData.execute(() -> {
            List<Service> list = new ArrayList<>();
            Cursor cursor = null;
            try {
                cursor = db.rawQuery(sql, args);
                while (cursor.moveToNext()) {
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
                onDataLoadedListener.onDataLoaded(list);
            } catch (Exception e) {
                // Gửi thông báo lỗi về thông qua callback
                onDataLoadedListener.onError(e.getMessage());
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                // Đảm bảo ExecutorService được đóng sau khi công việc đã hoàn tất
                serviceLoadData.shutdown();
            }
        });
    }
    @SuppressLint("Range")
    private List<Service> get(String sql, String...args){
        List<Service> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, args);
        while (cursor.moveToNext()) {
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
        return list;
    }
    // thu nghiem
    @SuppressLint("Range")
    public void getAsync3(String sql, OderFragmentInterFace oderFragmentInterFace, String...args) {
        ExecutorService serviceLoadData = Executors.newSingleThreadExecutor();
        serviceLoadData.execute(() -> {
            List<Service> list = new ArrayList<>();
            Cursor cursor = null;
            try {
                cursor = db.rawQuery(sql, args);
                while (cursor.moveToNext()) {
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
                oderFragmentInterFace.getDataSuccess(list);
            } catch (Exception e) {
                // Gửi thông báo lỗi về thông qua callback
                oderFragmentInterFace.getDataError(e.getMessage());
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                // Đảm bảo ExecutorService được đóng sau khi công việc đã hoàn tất
                serviceLoadData.shutdown();
            }
        });
    }

    //
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
        return getAsync(sql).join();
    }
    public Service queryByName (String queryName){
        String sql = "SELECT * FROM service WHERE name = ?";
        List<Service> list = getAsync(sql,queryName).join();
        if (list.size() == 0) return null;
        else  return  list.get(0);
    }
    public Service queryByID (int  id){
        String sql = "SELECT * FROM service WHERE serviceID = ?";
        List<Service> list = get(sql, String.valueOf(id));
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
