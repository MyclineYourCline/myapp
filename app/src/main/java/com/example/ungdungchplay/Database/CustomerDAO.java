package com.example.ungdungchplay.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ungdungchplay.ModelManager.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private SQLiteDatabase db;
    private SqlOpenHelper helper;

    public CustomerDAO(Context context) {
        helper = new SqlOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    // "id INTEGER PRIMARY KEY AUTOINCREMENT," +
    ////            "name TEXT," +
    ////            "phone TEXT," +
    ////            "address TEXT," +
    ////            "birth DATE)";

    @SuppressLint("Range")
    public List<Customer> get (String sql, String...args){
        List<Customer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,args);
        while (cursor.moveToNext()){
            Customer item = new Customer();
            item.setId(cursor.getInt(cursor.getColumnIndex("id")));
            item.setName(cursor.getString(cursor.getColumnIndex("name")));
            item.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            item.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
            item.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            list.add(item);
        }
        cursor.close();
        return list;
    }
    public List<Customer> getALL (){
        String sql = "SELECT * FROM customer";
        return get(sql);
    }
    public Customer getByID (int id){
        String sql = "SELECT * FROM customer WHERE id = ?";
        Customer result = get(sql, String.valueOf(id)).get(0);
        return result;
    }
    public long insertCustomer (Customer customer){
        ContentValues values = new ContentValues();
        values.put("id",customer.getId());
        values.put("name", customer.getName());
        values.put("phone", customer.getPhone());
        values.put("birth", customer.getBirth());
        values.put("address", customer.getAddress());
        return db.insert("customer",null,values);
    }
    public int deleteCustomer (int id){
        return db.delete("customer","id = ?", new String[]{String.valueOf(id)});
    }
    public int  updateCustomer (Customer customer){
        ContentValues values = new ContentValues();
        values.put("id",customer.getId());
        values.put("name", customer.getName());
        values.put("phone", customer.getPhone());
        values.put("birth", customer.getBirth());
        values.put("address", customer.getAddress());
        return db.update("customer",values,"id = ? ",
                new String[]{String.valueOf(customer.getId())});
    }

}
