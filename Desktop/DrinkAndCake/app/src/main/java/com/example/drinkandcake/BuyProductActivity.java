package com.example.drinkandcake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drinkandcake.model.Product;

public class BuyProductActivity extends AppCompatActivity {
    private TextView name,price,total,quantity;
    Button btnDatHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);

        name = findViewById(R.id.nameP);
        price = findViewById(R.id.priceP);
        total = findViewById(R.id.total);
        btnDatHang = findViewById(R.id.btnDatHang);
        quantity = findViewById(R.id.quantity);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("XNProduct");
        Product product = (Product) bundle.getSerializable("product");

        name.setText(product.getName());
        price.setText(product.getPrice()+"");
        total.setText(product.getPrice()+20000+"đ");
        quantity.setText(product.getQuantity()+"");

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BuyProductActivity.this,ThanhCongActivity.class);
                startActivity(intent1);
            }
        });

        //Toast.makeText(this, product.getName(), Toast.LENGTH_SHORT).show();
    }
}