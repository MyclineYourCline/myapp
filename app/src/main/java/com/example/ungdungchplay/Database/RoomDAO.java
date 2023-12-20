package com.example.ungdungchplay.Database;

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

        // Kiểm tra Cursor trước khi truy cập dữ liệu
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Room room = new Room();

                // Kiểm tra sự tồn tại của cột trước khi đọc giá trị
                int idColumnIndex = cursor.getColumnIndex("id");
                int nameColumnIndex = cursor.getColumnIndex("name");

                if (idColumnIndex != -1) {
                    room.setId(cursor.getInt(idColumnIndex));
                }

                if (nameColumnIndex != -1) {
                    room.setName(cursor.getString(nameColumnIndex));
                }

                list.add(room);
            } while (cursor.moveToNext());

            cursor.close();
        }

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
    public Room getByName(String name){
        String sql = "SELECT * FROM room WHERE name = ?";
        if (get(sql,name).size() == 0) return null;
        else return get(sql,name).get(0);
    }
}
