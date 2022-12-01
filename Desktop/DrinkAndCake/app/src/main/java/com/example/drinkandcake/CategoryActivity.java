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
import com.example.drinkandcake.sqlite.ProductDao;

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
        ProductDao productDao = new ProductDao(this);

        List<Product> list = new ArrayList<>();
        list = productDao.getALL();

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
            public void onClickCart(Product product,int quantity) {
                clickOrder(product,quantity);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void clickBuy(Product product) {
        Intent intent = new Intent(CategoryActivity.this,XacNhanDonHang.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productId",product);
        intent.putExtra("Buy",bundle);
        startActivity(intent);
    }

    private void clickOrder(Product product,int quantity) {
        Intent intent = new Intent(this,OderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productId",product);
        bundle.putInt("quantity",quantity);
        intent.putExtra("Order",bundle);
        startActivity(intent);
    }
}