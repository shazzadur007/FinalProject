package com.example.daamdekhi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    public static String[][] products = new String[1][4];
    public static View[] productsView = new View[10];
    public static LinearLayout searchResultView;
    public static TextView searchTest;
    Context cont;
    public static double    latitude = MainActivity.latitude,
                            longitude = MainActivity.longitude;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.cont = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Bundle data = getArguments();

        searchResultView = v.findViewById(R.id.searchResultView);
        searchTest = v.findViewById(R.id.searchTest);
        if( data == null || data.getString("query").equals("") ) {
            searchTest.setText("");
            fetchProducts process = new fetchProducts(this.cont, null);
            process.execute();
        } else {
            searchTest.setText("Searching: " + data.getString("query"));
            String q = data.getString("query");
            fetchProducts process = new fetchProducts(this.cont, q);
            process.execute();
        }
        return v;
    }
}
