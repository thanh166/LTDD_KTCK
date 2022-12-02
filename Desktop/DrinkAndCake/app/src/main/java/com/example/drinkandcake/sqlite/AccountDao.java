package com.example.drinkandcake.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.drinkandcake.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private SQLiteDatabase sqLiteDatabase;

    public AccountDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getReadableDatabase();
    }
    @SuppressLint("Range")
    public List<Account> get(String sql, String ...selectArgs){
        List<Account> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            Account account = new Account();
            account.setId(cursor.getInt(cursor.getColumnIndex("id")));
            account.setName(cursor.getString(cursor.getColumnIndex("name")));
            account.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            account.setRole(cursor.getString(cursor.getColumnIndex("role")));
            account.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            account.setAddress(cursor.getString(cursor.getColumnIndex("address")));

            list.add(account);
        }
        return list;
    }

    public List<Account> getALL(){
        String sql = "SELECT * FROM account";
        return get(sql);
    }

    public long insert(Account account){
        ContentValues contentValues = new ContentValues();
        //contentValues.put("id",account.getId());
        contentValues.put("name",account.getName());
        contentValues.put("password",account.getPassword());
        contentValues.put("role",account.getRole());
        contentValues.put("phone",account.getPhone());
        contentValues.put("address",account.getAddress());
        return sqLiteDatabase.insert("account",null,contentValues);
    }
}
