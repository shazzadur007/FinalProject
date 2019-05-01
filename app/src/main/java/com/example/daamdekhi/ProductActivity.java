package com.example.daamdekhi;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    private TextView tvtitle,tvdescription,tvCategory;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);




        tvtitle=(TextView)findViewById(R.id.txttitle);
        tvdescription=(TextView)findViewById(R.id.txtdesc);
        tvCategory=(TextView)findViewById(R.id.txtCat);
        img=(ImageView)findViewById(R.id.productthumbnail);

        //Recive data

        Intent intent=getIntent();
        String Title=intent.getExtras().getString("Title");
        String category=intent.getExtras().getString("Category");
        String Description=intent.getExtras().getString("Description");
        int image =intent.getExtras().getInt("Thumbnail");

        //setting values

        tvtitle.setText(Title);
        tvCategory.setText(category);
        tvdescription.setText(Description);
        img.setImageResource(image);
    }
}
