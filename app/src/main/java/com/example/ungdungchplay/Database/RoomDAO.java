package com.example.ungdungchplay.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ungdungchplay.ModelManager.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    // "create table room (id INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "name TEXT)";
    private SqlOpenHelper helper;
    private SQLiteDatabase db;

    public RoomDAO(Context context) {
        helper = new SqlOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<Room> get (String sql, String ...args){
        List<Room> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,args);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Room room = new Room();
            room.setId(cursor.getInt(cursor.getColumnIndex("id")));
            room.setName(cursor.getString(cursor.getColumnIndex("name")));
            list.add(room);
        }
        return list;
    }
    public Long insertRoom (Room room){
        ContentValues values = new ContentValues();
        values.put("id", room.getId());
        values.put("name", room.getName());
        return db.insert("room", null, values);
    }
    public int updateRoom(Room room){
        ContentValues values = new ContentValues();
        values.put("id", room.getId());
        values.put("name", room.getName());
        return db.update("room", values, "id = ?",
                new String[]{String.valueOf(room.getId())});
    }
    public int deleteRoom (int id){
        return db.delete("room","id = ?", new String[]{String.valueOf(id)});
    }
}
