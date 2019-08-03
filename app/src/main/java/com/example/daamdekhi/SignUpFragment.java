package com.example.daamdekhi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class SignUpFragment extends Fragment {
    public static EditText Username;
    public static EditText Password;
    public static EditText Name;
    public static EditText Nid;
    public static EditText Address;
    public static Double Latitude;
    public static Double Longitude;
    public static EditText Phone;
    public static EditText Email;
    private Context cont;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Username = container.findViewById(R.id.registerusername);
        Password = container.findViewById(R.id.registerpassword);
        Name = container.findViewById(R.id.registername);
        Nid = container.findViewById(R.id.registernid);
        Address = container.findViewById(R.id.registeraddress);
        Phone = container.findViewById(R.id.registerphone);
        Email = container.findViewById(R.id.registeremail);


        Latitude = MainActivity.latitude;
        Longitude = MainActivity.longitude;

        createUser process = new createUser(cont);
        process.execute();
        
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }


}
