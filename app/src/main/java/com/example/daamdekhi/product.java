package com.example.daamdekhi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class product extends AppCompatActivity {
    private String Title;
    private  String Category;
    private String Description;
    private int Thumbnail;
    public static LinearLayout searchResultView;
    public static String[][] products = new String[1][4];
    public static View[] productsView = new View[10];



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchResultView = findViewById(R.id.searchResultView);

        fetchProductsByCategory process = new fetchProductsByCategory(this, Title);
        process.execute();
    }

    public product(String title, String category, String description, int thumbnail) {
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
    }

//    public String getTitle() {
//        return Title;
//    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
