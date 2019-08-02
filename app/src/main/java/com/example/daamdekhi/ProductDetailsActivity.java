package com.example.daamdekhi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ProductDetailsActivity extends AppCompatActivity {

    public static TextView productPrice;
    public static TextView productDesc;
    public static TextView productName;
    public static TextView productMeta;
    public static ImageView productImage;
    public static RatingBar productRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productPrice = findViewById(R.id.productPrice);
        productDesc = findViewById(R.id.descriptionText);
        productName = findViewById(R.id.productName);
        productMeta = findViewById(R.id.metaText);
        productImage = findViewById(R.id.productImage);
        productRating = findViewById(R.id.productRating);

        Intent myIntent = getIntent();

        productPrice.setText("" + myIntent.getStringExtra("ProductPrice"));
        productDesc.setText(myIntent.getStringExtra("ProductDesc"));
        productName.setText(myIntent.getStringExtra("ProductName"));
        productMeta.setText(myIntent.getStringExtra("ProductMeta"));
    }
}
