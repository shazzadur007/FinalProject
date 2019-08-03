package com.example.daamdekhi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class createUser extends AsyncTask<Void, Void, Void> {
    private String data = "";
    Context cont;

    public createUser(Context cont) {
        super();
        this.cont = cont;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String Username =  SignUpFragment.Username.getText().toString();
            String Password =  SignUpFragment.Password.getText().toString();
            String Name =  SignUpFragment.Name.getText().toString();
            String Nid =  SignUpFragment.Nid.getText().toString();
            String Address =  SignUpFragment.Address.getText().toString();
            String Phone =  SignUpFragment.Phone.getText().toString();
            String Email =  SignUpFragment.Email.getText().toString();

            String u = "https://daamdekhi.com/api/user/create.php?userid="+Username+"&password="+Password+"&name="+Name+"&nid="+Nid+"&address="+Address+"&latitude="+ MainActivity.latitude +"&longitude="+ MainActivity.longitude +"&phoneno="+Phone+"&email="+Email+"";
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
            } else {
                Log.d("ERRORGET", "GET request not worked");
            }
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

        Toast.makeText(cont, "User Created", Toast.LENGTH_LONG).show();

        SignUpFragment.Username.setText("");
        SignUpFragment.Password.setText("");
        SignUpFragment.Name.setText("");
        SignUpFragment.Nid.setText("");
        SignUpFragment.Address.setText("");
        SignUpFragment.Phone.setText("");
        SignUpFragment.Email.setText("");
    }
}
