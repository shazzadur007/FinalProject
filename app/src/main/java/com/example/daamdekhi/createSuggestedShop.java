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
        import android.widget.Toast;

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

public class createSuggestedShop extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private Double distance;
    String[][] shops;
    String[] shopslist;
    Context cont;

    public createSuggestedShop(Context cont) {
        super();
        this.cont = cont;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String shopname =  SuggestedShop.inputshopname.getText().toString();
            String name =  SuggestedShop.inputname.getText().toString();
            String phone =  SuggestedShop.inputcontact.getText().toString();
            String mail =  SuggestedShop.inputmail.getText().toString();

            String u = "https://daamdekhi.com/api/shops/create.php?shopname="+ shopname +"&name="+ name + "&phone="+ phone + "&mail="+ mail + "";
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
                    data = line + data;
                }
            } else {
                Log.d("ERRORGET", "GET request not worked");
            }
            Log.d("LOGGING U", u);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Toast.makeText(cont, "Shop Request Created. Wait for approval.", Toast.LENGTH_LONG).show();

        SuggestedShop.inputshopname.setText("");
        SuggestedShop.inputname.setText("");
        SuggestedShop.inputcontact.setText("");
        SuggestedShop.inputmail.setText("");
    }
}
