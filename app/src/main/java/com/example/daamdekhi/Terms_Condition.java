package com.example.daamdekhi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Terms_Condition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms__condition);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Terms And Condition");
        setSupportActionBar(toolbar);
    }
}
