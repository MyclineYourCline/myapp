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
        cursor.moveToFirst();
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
        String sql = "SELECT * FROM table";
        return get(sql);
    }
    public long insertTable (Table table){
        ContentValues values = new ContentValues();
        values.put("id",table.getId());
        values.put("roomID",table.getRoomID());
        values.put("name",table.getName());
        values.put("status",table.getStatus());
        return db.insert("table", null, values);
    }
    public int updateTable (Table table){
        ContentValues values = new ContentValues();
        values.put("id",table.getId());
        values.put("roomID",table.getRoomID());
        values.put("name",table.getName());
        values.put("status",table.getStatus());
        return db.update("table", values, "id = ?",
                new String[]{String.valueOf(table.getId())});
    }
    public int deleteTable  (int id){
        return db.delete("table", "id = ?", new String[]{String.valueOf(id)});
    }
}
