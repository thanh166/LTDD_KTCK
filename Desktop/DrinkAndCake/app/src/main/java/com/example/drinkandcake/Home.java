package com.example.drinkandcake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.rcv_food);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);

        FoodAdapter adapter = new FoodAdapter(getListFood());
        recyclerView.setAdapter(adapter);
    }

    private List<Product> getListFood() {
        List<Product> mList = new ArrayList<>();
        mList.add(new Product(1,"cafe 1",10000,R.drawable.cafe));
        mList.add(new Product(2,"cafe 2",10000,R.drawable.cafe));
        mList.add(new Product(3,"cafe 3",10000,R.drawable.cafe));
        mList.add(new Product(4,"cafe 4",10000,R.drawable.cafe));
        mList.add(new Product(1,"cafe 1",10000,R.drawable.cafe));
        mList.add(new Product(2,"cafe 2",10000,R.drawable.cafe));
        mList.add(new Product(3,"cafe 3",10000,R.drawable.cafe));
        mList.add(new Product(4,"cafe 4",10000,R.drawable.cafe));
        return mList;
    }
}