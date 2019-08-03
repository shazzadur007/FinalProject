package com.example.daamdekhi;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    private TextView tvtitle,tvdescription,tvCategory;
    private ImageView img;

    public static LinearLayout searchResultView;
    public static String[][] products = new String[1][4];
    public static View[] productsView = new View[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Product");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        tvtitle=(TextView)findViewById(R.id.txttitle);
//        tvdescription=(TextView)findViewById(R.id.txtdesc);
//        tvCategory=(TextView)findViewById(R.id.txtCat);
        img=(ImageView)findViewById(R.id.productthumbnail);

        //Recive data

        Intent intent=getIntent();
        String Title=intent.getExtras().getString("Title");
//        String category=intent.getExtras().getString("Category");
//        String Description=intent.getExtras().getString("Description");
        int image =intent.getExtras().getInt("Thumbnail");

        //setting values

        tvtitle.setText(Title);
//        tvCategory.setText(category);
//        tvdescription.setText(Description);
        img.setImageResource(image);


        searchResultView = findViewById(R.id.searchResultView);

        fetchProductsByCategory process = new fetchProductsByCategory(this, Title.toLowerCase());
        process.execute();
    }
}
