package com.example.daamdekhi;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

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

public class validateLogin extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private String userid;
    private String password;
    Context cont;

    public validateLogin(Context cont) {
        super();
        this.cont = cont;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String u = "https://daamdekhi.com/api/user/read.php?id=" + LoginFragment.username;
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
                JSONObject result = new JSONObject(data);
                userid = (String) result.get("userid");
                password = (String) result.get("password");
            } else {
                Log.d("ERRORGET", "GET request not worked");
            }
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        LoginFragment.logged = true;
        if (LoginFragment.username.equals(userid) && LoginFragment.hashpass.equals(password)) {
            Log.d("LOGIN", "Login successful!");
        }
    }
}
