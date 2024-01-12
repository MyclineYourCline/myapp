package com.example.ungdungchplay.Database;

import static android.util.Log.d;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ungdungchplay.ModelManager.Room;
import com.example.ungdungchplay.R;

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
    public List<Room> get(String sql, String... args) {
        List<Room> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, args);
            while (cursor.moveToNext()) {
                Room room = new Room();
                room.setId(cursor.getInt(cursor.getColumnIndex("roomID")));
                room.setName(cursor.getString(cursor.getColumnIndex("name")));
                list.add(room);
            }
            cursor.close();
        return list;
    }

    public Long insertRoom (Room room){
        ContentValues values = new ContentValues();
        values.put("name", room.getName());
        return db.insert("room", null, values);
    }
    public List<Room> getAll (){
        String sql = "SELECT * FROM room";
        List<Room> list = get(sql);
        if (list.size() == 0)  return null;
        else return list;
    }
    public Room queryById (String id){
        String sql = "SELECT * FROM room Where roomID = ?";
        List<Room> list = get(sql,id);
        if (list.size() == 0) return null;
        else return list.get(0);
    }

    public int updateRoom(Room room){
        ContentValues values = new ContentValues();
        values.put("roomID", room.getId());
        values.put("name", room.getName());
        return db.update("room", values, "roomID = ?",
                new String[]{String.valueOf(room.getId())});
    }
    public int deleteRoom (int id){
        return db.delete("room","roomID = ?", new String[]{String.valueOf(id)});
    }
    public Room getByName(String name){
        String sql = "SELECT * FROM room WHERE name = ?";
        if (get(sql,name).size() == 0) return null;
        else return get(sql,name).get(0);
    }
}
