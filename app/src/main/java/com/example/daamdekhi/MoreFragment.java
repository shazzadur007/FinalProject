package com.example.daamdekhi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;


public class MoreFragment extends Fragment {

    private Button enlisted_shop;
    private Button suggested_shop;
    private Button like_facebook;
    private Button foll0w_insta;
    private Button about_us;
    private Button rate_us;
    private Button terms_condition;
    private Button privacy_policy;
    private Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_more, container, false);


        enlisted_shop=view.findViewById(R.id.EnlistedShop);
        enlisted_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EnlistedShop.class);
                startActivity(intent);

            }
        });

        suggested_shop=view.findViewById(R.id.SuggestedShop);
        suggested_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),SuggestedShop.class);
                startActivity(intent);
            }
        });






        return view;
     
    }


}
