package com.example.daamdekhi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class HomeFragment extends Fragment {
    public String[][] products = new String[][]{
            {"Rice in Dhaka", "Small desc", "100", "image.jpg"},
            {"Rice in Chittagong", "Small desc", "100", "image.jpg"},
            {"Rice in Comilla", "Small desc", "100", "image.jpg"}
    };
    View[] productsView = new View[3];
    String descHolder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Bundle data = getArguments();
        LinearLayout searchResultView = v.findViewById(R.id.searchResultView);
        TextView searchTest = (TextView) v.findViewById(R.id.searchTest);
        if( data == null ) {
            searchTest.setText("No result Found!");
        } else {
            searchTest.setText("Searching: " + data.getString("query"));
            String q = data.getString("query");
            for ( int i = 0; i < 3; i++ ) {
                productsView[i] = inflater.inflate(R.layout.product_search_item, null);
                TextView productName = productsView[i].findViewById(R.id.productSingleName);
                productName.setText(products[i][0]);
                searchResultView.addView(productsView[i], i);
            }
        }
        return v;
    }
}
