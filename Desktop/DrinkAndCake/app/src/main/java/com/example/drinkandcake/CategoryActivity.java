package com.example.drinkandcake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.drinkandcake.model.Product;
import com.example.drinkandcake.my_interface.IClickItemProductListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button cong,tru;
    private TextView quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        List<Product> list = new ArrayList<>();
        list.add(new Product("1", "tra sua", 20));
        list.add(new Product("2", "tra sua", 20));
        list.add(new Product("3", "tra sua", 20));

        recyclerView = findViewById(R.id.RcvCategory);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        CategoryAdapter adapter = new CategoryAdapter(list, new IClickItemProductListener() {
            @Override
            public void onClickItemProduct(Product product) {
            }

            @Override
            public void onClickBuy(Product product) {
                clickBuy(product);
            }
            @Override
            public void onClickCart(Product product) {
                clickOrder(product);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void clickBuy(Product product) {
        Intent intent = new Intent(this,XacNhanDonHang.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productBuy",product);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void clickOrder(Product product) {
        Intent intent = new Intent(this,OderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productOrder",product);
        intent.putExtra("order",bundle);
        startActivity(intent);
    }
}