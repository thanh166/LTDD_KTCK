package com.example.drinkandcake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Toast.makeText(ProfileActivity.this, "home ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfileActivity.this, Home.class);
                        startActivity(intent);
                        break;
                    case R.id.action_cart:
                        Toast.makeText(ProfileActivity.this, "cart ", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(ProfileActivity.this, OderActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_history:
                        Toast.makeText(ProfileActivity.this, "history ", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(ProfileActivity.this, HistoryActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_profile:
                        Toast.makeText(ProfileActivity.this, "profile ", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}