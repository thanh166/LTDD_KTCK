package com.example.drinkandcake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);

        CartDao cartDao = new CartDao(this);

        SharedPreferences myR = getSharedPreferences("AccountActivity",MODE_PRIVATE);
        int idU = myR.getInt("idUser",0);
        String nameU = myR.getString("nameUser","");

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Order");
        Product product = (Product) bundle.getSerializable("productId");
        int quantity = bundle.getInt("quantity");

        Cart cart = new Cart(Integer.parseInt(product.getId()),idU,quantity);
        cartDao.insert(cart);
        ProductDao productDao = new ProductDao(this);
        List<Product> mListP = new ArrayList<>();
        List<Cart> mListCart = new ArrayList<>();
        mListCart = cartDao.getByIdUser(idU+"");
        for(Cart a : mListCart){
            mListP.add(productDao.getById(a.getIdP() +""));
        }
        recyclerView = findViewById(R.id.RcvCategory2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        CategoryAdapter adapter = new CategoryAdapter(mListP, new IClickItemProductListener() {
            @Override
            public void onClickItemProduct(Product product) {
            }
            @Override
            public void onClickBuy(Product product) {
            }
            @Override
            public void onClickCart(Product product, int quantity) {
            }
        });
        recyclerView.setAdapter(adapter);
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
}