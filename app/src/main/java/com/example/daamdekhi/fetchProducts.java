package com.example.daamdekhi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
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

public class fetchProducts extends AsyncTask<Void, Void, Void> {
    private String _name = "";
    private String data = "";
    private String name = "";
    private String price = "";
    private String desc = "";
    private String meta = "";
    private String image = "";
    private Integer rating = null;
    private String query = "";
    String[][] products;
    Context cont;
    public fetchProducts(Context cont, String query) {
        super();
        this.cont = cont;
        if(query == null) {
            this.query = "";
        } else {
            this.query = query;
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String u = "https://daamdekhi.com/api/product/read.php?id=102";
            URL url = new URL(u);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            InputStream inputStream;
            int status = httpURLConnection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
                String line = "";
                while ( (line = bufferedReader.readLine()) != null ) {
                    data = data + line;
                }
                JSONObject D = new JSONObject(data);
                String s = (String) D.get("message");

                if( s.equals("ok") ) {
                    JSONArray JA = (JSONArray) D.get("products");
                    products = new String[JA.length()][4];
                    for ( int i = 0; i < JA.length(); i++) {
                        JSONObject JO = (JSONObject) JA.get(i);
                        products[i][0] = (String) JO.get("name");
                        products[i][1] = (String) JO.get("price");
                        products[i][2] = (String) JO.get("desc");
                        products[i][3] = (String) JO.get("meta");
                    };
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
        HomeFragment.products = products;
        HomeFragment.searchResultView.removeAllViews();
        HomeFragment.searchResultView.invalidate();
        View[] productsView = HomeFragment.productsView;
        LayoutInflater layoutInflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i=0; i<products.length; i++ ) {
            if( !products[i][0].toLowerCase().contains(query.toLowerCase()) ) {
                Log.d("CONTAINS", "F");
                continue;
            }
            productsView[i] = layoutInflater.inflate(R.layout.product_search_item, null);
            TextView productName = productsView[i].findViewById(R.id.productSingleName);
            TextView productSingleMeta = productsView[i].findViewById(R.id.productSingleMeta);
            productName.setText(products[i][0]);
            productSingleMeta.setText(products[i][1]);
            setOnClick(productsView[i], i);
            HomeFragment.searchResultView.addView(productsView[i]);
        }
        HomeFragment.searchResultView.invalidate();
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
}