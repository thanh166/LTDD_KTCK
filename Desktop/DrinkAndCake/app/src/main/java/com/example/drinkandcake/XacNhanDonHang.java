package com.example.drinkandcake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.drinkandcake.model.Product;

public class XacNhanDonHang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_don_hang);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Buy");
        Product product = (Product) bundle.getSerializable("productId");

        Toast.makeText(this, product.getName(), Toast.LENGTH_SHORT).show();
    }
}