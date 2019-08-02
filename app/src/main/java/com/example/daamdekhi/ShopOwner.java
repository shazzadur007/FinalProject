package com.example.daamdekhi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ShopOwner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_owner);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Shop Owner Info");
        setSupportActionBar(toolbar);


    }
}
