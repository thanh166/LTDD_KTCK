package com.example.drinkandcake.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.drinkandcake.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private SQLiteDatabase sqLiteDatabase;

    public ProductDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getReadableDatabase();
    }

    @SuppressLint("Range")
    public List<Product> get(String sql, String ...selectArgs){
        List<Product> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            Product product = new Product();
            product.setId(cursor.getString(cursor.getColumnIndex("id")));
            product.setName(cursor.getString(cursor.getColumnIndex("name")));
            product.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
            product.setImage(cursor.getInt(cursor.getColumnIndex("image")));

            list.add(product);
        }
        return list;
    }

    public List<Product> getALL(){
        String sql = "SELECT * FROM product";
        return get(sql);
    }

    public Product getById(String id){
        String sql = "SELECT * FROM product WHERE id = ?";
        List<Product> list = get(sql, id);
        return list.get(0);
    }

    public long insert(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",product.getId());
        contentValues.put("name",product.getName());
        contentValues.put("price",product.getPrice());
        contentValues.put("image",product.getImage());
        return sqLiteDatabase.insert("product",null,contentValues);
    }

    public long update(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",product.getName());
        contentValues.put("price",product.getPrice());

        return sqLiteDatabase.update("product",contentValues,"id = ?",new String[]{product.getId()});
    }

    public int delete(String id){
        return sqLiteDatabase.delete("product","id = ?",new String[]{id});
    }
}
