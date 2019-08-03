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
        lstproduct.add(new product("Women","Category","Description",R.drawable.women));
        lstproduct.add(new product("Men","Category","Description",R.drawable.men));
        lstproduct.add(new product("Kid's","Category ","Description",R.drawable.kid));
        lstproduct.add(new product("Gift","Category","Description",R.drawable.gift));
        lstproduct.add(new product("Wearable Device","Category","Description",R.drawable.wearable));
        lstproduct.add(new product("Health Care","Category","Description",R.drawable.healthcare));
        lstproduct.add(new product("Home Appliance","Category","Description",R.drawable.home));
        lstproduct.add(new product("Computer's & Camera's","Category ","Description",R.drawable.computers));
        lstproduct.add(new product("Security & Safety","Category","Description",R.drawable.security));



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
