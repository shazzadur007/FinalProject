package com.example.daamdekhi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class fetchEnlistedShops extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private Double distance;
    String[][] shops;
    String[] shopslist;
    Context cont;

    public fetchEnlistedShops(Context cont) {
        super();
        this.cont = cont;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String u = "https://daamdekhi.com/api/shops/read.php";
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
                JSONArray JA = (JSONArray) D.getJSONArray("Shops");
                shops = new String[JA.length()][5];
                shopslist = new String[JA.length()];
                for (int i = 0; i < JA.length(); i++) {
                    JSONObject JO = (JSONObject) JA.get(i);
                    shopslist[i] = (String) JO.get("shopname");
                    shops[i][0] = (String) JO.get("shopid");
                    shops[i][1] = (String) JO.get("shopname");
                    shops[i][2] = (String) JO.get("name");
                    shops[i][3] = (String) JO.get("phone");
                    shops[i][4] = (String) JO.get("mail");
                }
                Log.d("ERROR D", shopslist[0]);
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

        ArrayAdapter adapter = new ArrayAdapter<String>(cont, R.layout.enlisted_shop_item, shopslist);
        EnlistedShop.listView.setAdapter(adapter);
    }
}
