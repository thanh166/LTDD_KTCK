package com.example.drinkandcake.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "DrinkAndCake";
    public static final int DB_VERSION = 5;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE product(id text primary key,name text not null, price real not null,int image );";
        String sqlAcc = "CREATE TABLE account(id text primary key,name text not null,password text not null, role text not null) ";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sqlAcc);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql ="DROP TABLE IF EXISTS product;";
        String sqlAccount ="DROP TABLE IF EXISTS account;";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sqlAccount);
        onCreate(sqLiteDatabase);
    }


}
