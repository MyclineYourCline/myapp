package com.example.ungdungchplay.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlOpenHelper extends SQLiteOpenHelper{
    private final static String DB_NAME = "ResTof";
    private final static int VERSION =  1;

    public SqlOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbStruct.CREATE_TABLE_BILL);
        db.execSQL(DbStruct.CREATE_TABLE_CUSTOMER);
        db.execSQL(DbStruct.CREATE_TABLE_USER);
        db.execSQL(DbStruct.CREATE_TABLE_ROOM);
        db.execSQL(DbStruct.CREATE_TABLE_TABLE);
        db.execSQL(DbStruct.CREATE_TABLE_SERVICE);
        db.execSQL(DbStruct.CREATE_TABLE_detailSERVICE);
        db.execSQL(DbStruct.CREATE_TABLE_ODER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbStruct.DROP_TABLE_BILL);
        db.execSQL(DbStruct.DROP_TABLE_BILLDetail);
        db.execSQL(DbStruct.DROP_TABLE_CUSTOMER);
        db.execSQL(DbStruct.DROP_TABLE_USER);
        db.execSQL(DbStruct.DROP_TABLE_ROOM);
        db.execSQL(DbStruct.DROP_TABLE_TABLE);
        db.execSQL(DbStruct.DROP_TABLE_SERVICE);
        db.execSQL(DbStruct.DROP_TABLE_ODER);
        onCreate(db);
    }
}
