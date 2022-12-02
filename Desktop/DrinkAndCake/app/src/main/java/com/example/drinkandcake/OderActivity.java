package com.example.drinkandcake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drinkandcake.model.Cart;
import com.example.drinkandcake.model.Product;
import com.example.drinkandcake.my_interface.IClickItemProductListener;
import com.example.drinkandcake.sqlite.CartDao;
import com.example.drinkandcake.sqlite.ProductDao;

import java.util.ArrayList;
import java.util.List;

public class OderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnDathang;
    private List<Product> mListP;
    private TextView totalPr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);

        btnDathang = findViewById(R.id.btnDathang);
        totalPr = findViewById(R.id.totalPr);

        CartDao cartDao = new CartDao(this);

        SharedPreferences myR = getSharedPreferences("AccountActivity",MODE_PRIVATE);
        int idU = myR.getInt("idUser",0);

        ProductDao productDao = new ProductDao(this);
        mListP = new ArrayList<>();
        List<Cart> mListCart = new ArrayList<>();
        mListCart = cartDao.getByIdUser(idU+"");
        float totalPrice = 0;
        for(Cart a : mListCart){
            Product product = new Product();
            product = productDao.getById(a.getIdP() +"");
            product.setQuantity(a.getQuantity());
            mListP.add(product);
            totalPrice += product.getPrice()*product.getQuantity();
        }
        totalPr.setText(totalPrice+"đ");
        recyclerView = findViewById(R.id.RcvCategory2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        FoodCartAdapter adapter = new FoodCartAdapter(mListP, new IClickItemProductListener() {
            @Override
            public void onClickItemProduct(Product product) {
                onClickGoToDetail(product);
            }
            @Override
            public void onClickBuy(Product product) {
            }
            @Override
            public void onClickCart(Product product, int quantity) {
            }
        });
        recyclerView.setAdapter(adapter);

        btnDathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OderActivity.this,XacNhanDonHang.class);
                startActivity(intent);
            }
        });
    }
    private void onClickGoToDetail(Product product){
        Intent intent = new Intent(this,BuyProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product",product);
        intent.putExtra("XNProduct",bundle);
        startActivity(intent);
    }

}