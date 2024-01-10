package com.example.ungdungchplay.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ungdungchplay.ModelManager.Oder;
import com.example.ungdungchplay.ModelManager.Service;

import java.util.ArrayList;
import java.util.List;

public class  OderDAO {
    public static final int Pay = -1;
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
            oder.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
            list.add(oder);
        }
        return list;
    }
    public long insertOder (Oder oder){
        ContentValues values = new ContentValues();
        values.put("serviceID",oder.getServiceID());
        values.put("quantity",oder.getQuantity());
        values.put("tableID",oder.getTableID());
        values.put("status",Pay);
        return database.insert("oder", null,values);
    }
    public List<Oder> getByTableID (String tableID){
        String sql = "SELECT * FROM oder WHERE tableID = ?";
        return get(sql,tableID);
    }
    public List<Oder> queryByStatusAndTableID(int status,String taleID){
        String sql = "SELECT * FROM oder WHERE status = ? and tableID = ?";
        return get(sql,new String[]{String.valueOf(status),taleID});
    }
    public boolean checkOderServiceExist(String tableID, int status, int serviceID){
        String sql = "SELECT * FROM oder WHERE tableID = ? and status = ? and serviceID = ?";
        List<Oder> list =  get(sql,new String[]{tableID,String.valueOf(status),String.valueOf(serviceID)});
        if (list.size() != 0) return false;
        else return true;
    }
    public Oder queryByServiceIdAndTableIdAndStatus(String tableID, int status, int serviceID){
        String sql = "SELECT * FROM oder WHERE tableID = ? and status = ? and serviceID = ?";
        List<Oder> list = get(sql,new String[]{tableID,String.valueOf(status),String.valueOf(serviceID)});
        if (list.size() != 0) return list.get(0);
        else  return null;
    }
    public int updateOder(Oder oder){
        ContentValues values = new ContentValues();
        values.put("oderID",oder.getOderId());
        values.put("serviceID",oder.getServiceID());
        values.put("quantity",oder.getQuantity());
        values.put("tableID",oder.getTableID());
        values.put("status",oder.getStatus());
        return database.update("oder", values,"OderID = ?",
                new String[]{String.valueOf(oder.getOderId())});
    }
    public int deleteOder (Oder oder){
        return database.delete("oder","oderID = ?",
                new String[]{String.valueOf(oder.getOderId())});
    }
}
