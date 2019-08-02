package com.example.daamdekhi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SuggestedShop extends AppCompatActivity {

    public static EditText inputname;
    public static EditText inputshopname;
    public static EditText inputcontact;
    public static EditText inputmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Context cont = this;
        setContentView(R.layout.activity_suggested_shop);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Suggest Shop");
        setSupportActionBar(toolbar);

        inputname = findViewById(R.id.inputname);
        inputshopname = findViewById(R.id.inputshopname);
        inputcontact = findViewById(R.id.inputcontact);
        inputmail = findViewById(R.id.inputmail);

        final Button submitReq = (Button) findViewById(R.id.submitReq);
        submitReq.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSuggestedShop process = new createSuggestedShop(cont);
                process.execute();
            }
        });
    }
}
