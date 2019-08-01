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
    private Button faq;

    private Button about_us;
    private Button terms_condition;



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

        like_facebook=view.findViewById(R.id.LikeFacebook);
        like_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),FacebookActivity.class);
                startActivity(intent);
            }
        });
        faq=view.findViewById(R.id.Faq);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),FAQs.class);
                startActivity(intent);
            }
        });
        about_us=view.findViewById(R.id.AboutUS);
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),AboutUS.class);
                startActivity(intent);
            }
        });
        terms_condition=view.findViewById(R.id.TermsCondition);
        terms_condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Terms_Condition.class);
                startActivity(intent);
            }
        });







        return view;
     
    }


}
