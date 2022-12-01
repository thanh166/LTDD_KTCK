package com.example.drinkandcake.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "DrinkAndCake";
    public static final int DB_VERSION = 10;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE product(id integer primary key,name text not null, price real not null,image text );";
        String sqlAcc = "CREATE TABLE account(id integer primary key,name text not null,password text not null, role text not null) ";
        String sqlCart = "CREATE TABLE cart(id integer primary key,idP integer not null,idUser integer not null, quantity integer not null) ";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sqlCart);
        sqLiteDatabase.execSQL(sqlAcc);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql ="DROP TABLE IF EXISTS product;";
        String sqlAccount ="DROP TABLE IF EXISTS account;";
        String sqlCart ="DROP TABLE IF EXISTS cart;";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sqlAccount);
        sqLiteDatabase.execSQL(sqlCart);
        onCreate(sqLiteDatabase);
    }


}
