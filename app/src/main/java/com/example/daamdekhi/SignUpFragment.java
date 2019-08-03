package com.example.daamdekhi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Username = view.findViewById(R.id.registerusername);
        Password = view.findViewById(R.id.registerpassword);
        Name = view.findViewById(R.id.registername);
        Nid = view.findViewById(R.id.registernid);
        Address = view.findViewById(R.id.registeraddress);
        Phone = view.findViewById(R.id.registerphone);
        Email = view.findViewById(R.id.registeremail);

        Latitude = MainActivity.latitude;
        Longitude = MainActivity.longitude;

        Button submit = view.findViewById(R.id.submitReq);
        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser process = new createUser(MainActivity.cont);
                process.execute();
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }


}
