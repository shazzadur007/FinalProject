package com.example.daamdekhi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginFragment extends Fragment {
    private Button login;
    private Button Signup;
    private SignUpFragment signUpFragment;
    private EditText usernameView;
    public static String username;
    private EditText passwordView;
    public static String password;
    public static String hashpass;
    public static boolean logged = false;
    Context cont;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.cont = context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        Signup=view.findViewById(R.id.userSignUpButton);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.main_frame,new SignUpFragment() );
                fr.commit();



            }
        });

        login=view.findViewById(R.id.signInButton);
        usernameView = view.findViewById(R.id.userId);
        passwordView = view.findViewById(R.id.userpassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameView.getText().toString();
                password = passwordView.getText().toString();
                hashpass = getMd5(password);
                validateLogin process = new validateLogin(cont);
                process.execute();
                if ( logged ) {
                    Log.d("Logged", "logged");
                    Fragment fragment=new ProfileFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_frame,fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Log.d("Logged", "not logged");
                }
            }
        });



        return view;

    }

    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}
