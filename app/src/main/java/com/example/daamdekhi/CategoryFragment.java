package com.example.daamdekhi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    private List<product> lstproduct;
    private RecyclerView myrv;
    private RecyclerViewAdapter myAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_category, container, false);
        NestedScrollView scrollView = (NestedScrollView)view.findViewById (R.id.nest_scrollview);
        scrollView.setFillViewport (true);
        lstproduct= new ArrayList<>();
        lstproduct.add(new product("Art","Categorie Book","Description Book",R.drawable.art));
        lstproduct.add(new product("Doutch","Categorie Book","Description Book",R.drawable.doutch));
        lstproduct.add(new product("KawaSaki","Categorie Book","Description Book",R.drawable.kawasaki));
        lstproduct.add(new product("Fred","Categorie Book","Description Book",R.drawable.fred));
        lstproduct.add(new product("Murphy","Categorie Book","Description Book",R.drawable.murphy));
        lstproduct.add(new product("Sugar","Categorie Book","Description Book",R.drawable.sugar));

        lstproduct.add(new product("Art","Categorie Book","Description Book",R.drawable.art));
        lstproduct.add(new product("Doutch","Categorie Book","Description Book",R.drawable.doutch));
        lstproduct.add(new product("KawaSaki","Categorie Book","Description Book",R.drawable.kawasaki));
        lstproduct.add(new product("Fred","Categorie Book","Description Book",R.drawable.fred));
        lstproduct.add(new product("Murphy","Categorie Book","Description Book",R.drawable.murphy));
        lstproduct.add(new product("Sugar","Categorie Book","Description Book",R.drawable.sugar));

        lstproduct.add(new product("Art","Categorie Book","Description Book",R.drawable.art));
        lstproduct.add(new product("Doutch","Categorie Book","Description Book",R.drawable.doutch));
        lstproduct.add(new product("KawaSaki","Categorie Book","Description Book",R.drawable.kawasaki));
        lstproduct.add(new product("Fred","Categorie Book","Description Book",R.drawable.fred));
        lstproduct.add(new product("Murphy","Categorie Book","Description Book",R.drawable.murphy));
        lstproduct.add(new product("Sugar","Categorie Book","Description Book",R.drawable.sugar));







        myrv= (RecyclerView)view.findViewById(R.id.recyclerview_id);
        myrv.setHasFixedSize(true);
        myrv.setNestedScrollingEnabled(false);
        myrv.setItemViewCacheSize(40);
        myrv.setDrawingCacheEnabled(true);
        myrv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        myAdapter = new RecyclerViewAdapter(getActivity(),lstproduct);
        myrv.setLayoutManager(new GridLayoutManager(getActivity(),3));
        myrv.setAdapter(myAdapter);












        return view;
    }




}
