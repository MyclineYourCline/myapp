package com.example.ungdungchplay.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ungdungchplay.ModelManager.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // "create table user (" +
    //            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "name TEXT," +
    //            "address TEXT," +
    //            "phone TEXT," +
    //            "birth DATE," +
    //            "avatar INTEGER)";
    private SQLiteDatabase db;
    private SqlOpenHelper helper;

    public UserDAO(Context context) {
        helper = new SqlOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    private List<User> get (String sql, String...args){
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,args);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setAvatar(cursor.getInt(cursor.getColumnIndex("avatar")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            user.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
            list.add(user);
        }
        return list;
    }
    public List<User> getAll (){
        String sql = "SELECT * FROM user";
        return get(sql);
    }
    public long insertUser (User user){
        ContentValues values = new ContentValues();
        values.put("id",user.getId());
        values.put("avatar",user.getAvatar());
        values.put("name",user.getName());
        values.put("address",user.getAddress());
        values.put("phone",user.getPhone());
        values.put("birth",user.getBirth());
        return db.insert("user",null,values);
    }
    public long updateUser (User user){
        ContentValues values = new ContentValues();
        values.put("id",user.getId());
        values.put("avatar",user.getAvatar());
        values.put("name",user.getName());
        values.put("address",user.getAddress());
        values.put("phone",user.getPhone());
        values.put("birth",user.getBirth());
        return db.update("user",values, "id = ?",
                new String[]{String.valueOf(user.getId())});
    }
    public int deleteUser (int id){
        return db.delete("user", "id = ?", new String[]{String.valueOf(id)});
    }
    public User getById (int id){
        String sql = "SELECT * FROM  user WHERE id = ?";
        return get(sql).get(0);
    }
}
