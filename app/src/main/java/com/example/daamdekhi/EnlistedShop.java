package com.example.daamdekhi;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class EnlistedShop extends AppCompatActivity {

    public static ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlisted_shop);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Our Enlisted Shop");
        setSupportActionBar(toolbar);


        listView = (ListView) findViewById(R.id.shopList);

        fetchEnlistedShops process = new fetchEnlistedShops(this);
        process.execute();
    }
}
