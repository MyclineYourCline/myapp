package com.example.ungdungchplay.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ungdungchplay.ModelManager.Table;

import java.util.ArrayList;
import java.util.List;

public class TableDAO {
    //"create table table (id INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "roomID INTEGER," +
    //            "status INTEGER," +
     //            "name TEXT," +
    //            "FOREIGN KEY (roomID) REFERENCES room (id))";
    private SqlOpenHelper helper;
    private SQLiteDatabase db;

    public TableDAO(Context context) {
        helper = new SqlOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    private List<Table> get (String sql, String...args){
        List<Table>  list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,args);
        while (cursor.moveToNext()){
            Table table = new Table();
            table.setId(cursor.getInt(cursor.getColumnIndex("id")));
            table.setRoomID(cursor.getInt(cursor.getColumnIndex("roomID")));
            table.setName(cursor.getString(cursor.getColumnIndex("name")));
            table.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
            list.add(table);
        }
        return list;
    }
    public List<Table> getAll (){
        String sql = "SELECT * FROM tableRoom";
        return get(sql);
    }
    public long insertTable (Table table){
        ContentValues values = new ContentValues();
        values.put("roomID",table.getRoomID());
        values.put("name",table.getName());
        values.put("status",table.getStatus());
        return db.insert("tableRoom", null, values);
    }
    public Table getByNameAndRoomID(String name, int roomID){
        String sql = "SELECT * FROM tableRoom WHERE name = ? and roomID = ?";
        List<Table> list = get(sql,name,String.valueOf(roomID));
        if (list.size() ==  0) return null;
        else return list.get(0);
    }
    public List<Table> getByRoomID(int roomID){
        String sql = "SELECT * FROM tableRoom WHERE roomID = ?";
        List<Table> list = get(sql,String.valueOf(roomID));
        if (list.size() ==  0) return null;
        else return list;
    }
    public int updateTable (Table table){
        ContentValues values = new ContentValues();
        values.put("id",table.getId());
        values.put("roomID",table.getRoomID());
        values.put("name",table.getName());
        values.put("status",table.getStatus());
        return db.update("tableRoom", values, "id = ?",
                new String[]{String.valueOf(table.getId())});
    }
    public int deleteTable  (int id){
        return db.delete("tableRoom", "id = ?", new String[]{String.valueOf(id)});
    }
}
