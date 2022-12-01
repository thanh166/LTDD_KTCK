package com.example.drinkandcake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.drinkandcake.model.Product;
import com.example.drinkandcake.my_interface.IClickItemProductListener;
import com.example.drinkandcake.sqlite.DBHelper;
import com.example.drinkandcake.sqlite.ProductDao;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private RecyclerView recyclerView;
    CardView cafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cafe= findViewById(R.id.cafeCategory);
        onClickCafe();

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        inserP();


        recyclerView = findViewById(R.id.rcv_food);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);

        FoodAdapter adapter = new FoodAdapter(getListFood(), new IClickItemProductListener() {
            @Override
            public void onClickItemProduct(Product product) {
                onClickGoToDetail(product);
            }

            @Override
            public void onClickBuy(Product product) {
            }
            @Override
            public void onClickCart(Product product) {
            }
        });
        recyclerView.setAdapter(adapter);
        sqLiteDatabase.close();
    }




    private void onClickCafe() {
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Product> getListFood() {
        ProductDao productDao = new ProductDao(this);
        return productDao.getALL();
    }
    private void inserP(){
        ProductDao productDao = new ProductDao(this);
        Product product = new Product("25","tra sua",25000);
        productDao.insert(product);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences sharedPreferences = getSharedPreferences("mySave",MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//        myEdit.putString("idProduct","id");
//        myEdit.commit();
//
//    }
    private void onClickGoToDetail(Product product){
        Intent intent = new Intent(this,OderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productid",product);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}