package com.example.daamdekhi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchProductsByCategory extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private String category = "";
    private Double distance;
    String[][] products;
    Context cont;

    public fetchProductsByCategory(Context cont, String category) {
        super();
        this.cont = cont;
        if (category == null) {
            this.category = "";
        } else {
            this.category = category;
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String u = "https://daamdekhi.com/api/product/readByCategory.php?c=" + category + "";
            URL url = new URL(u);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            InputStream inputStream;
            int status = httpURLConnection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    data = data + line;
                }
                JSONObject D = new JSONObject(data);
                String s = (String) D.get("message");

                if (s.equals("ok")) {
                    JSONArray JA = (JSONArray) D.get("products");
                    products = new String[JA.length()][8];
                    for (int i = 0; i < JA.length(); i++) {
                        JSONObject JO = (JSONObject) JA.get(i);
                        products[i][0] = (String) JO.get("name");
                        products[i][1] = (String) JO.get("price") + " BDT";
                        products[i][2] = (String) JO.get("desc");
                        products[i][3] = (String) JO.get("meta");
                        products[i][4] = (String) JO.get("latitude");
                        products[i][5] = (String) JO.get("longitude");
                        products[i][6] = (String) JO.get("sellerId");

                        distance = distance( MainActivity.latitude, MainActivity.longitude, Double.parseDouble(products[i][4]), Double.parseDouble(products[i][5]), 'K');
                        products[i][7] = String.format("%.2f", distance) + " km";
                    }
                }
            } else {
                Log.d("ERRORGET", "GET request not worked");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ProductActivity.products = products;
        ProductActivity.searchResultView.removeAllViews();
        ProductActivity.searchResultView.invalidate();
        View[] productsView = ProductActivity.productsView;
        LayoutInflater layoutInflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i=0; i<products.length; i++ ) {
            productsView[i] = layoutInflater.inflate(R.layout.product_search_item, null);
            TextView productName = productsView[i].findViewById(R.id.productSingleName);
            TextView productSingleMeta = productsView[i].findViewById(R.id.productSingleMeta);
            TextView productSingleDist = productsView[i].findViewById(R.id.productSingleDist);
            productName.setText(products[i][0]);
            productSingleMeta.setText(products[i][1]);
            productSingleDist.setText(products[i][7]);
            setOnClick(productsView[i], i);
            ProductActivity.searchResultView.addView(productsView[i]);
        }
        ProductActivity.searchResultView.invalidate();
    }


    private void setOnClick(@NonNull View v, final int i){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cont, ProductDetailsActivity.class);
                intent.putExtra("ProductName", products[i][0]);
                intent.putExtra("ProductPrice", products[i][1]);
                intent.putExtra("ProductDesc", products[i][2]);
                intent.putExtra("ProductMeta", products[i][3]);
                cont.startActivity(intent);
            }
        });
    }


    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
