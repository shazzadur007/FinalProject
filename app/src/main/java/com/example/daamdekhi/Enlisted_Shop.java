package com.example.daamdekhi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Enlisted_Shop extends Fragment {
    GridView gridView;
    String[] numberWord={"One","Two","Three","Four","Five","Six","Seven","Eight", "Nine","Ten"};
    int [] numberImage={R.drawable.one, R.drawable.two,R.drawable.three, R.drawable.four, R.drawable.five,
            R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine,R.drawable.ten};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_enlisted__shop, container, false);
        // Inflate the layout for this fragment

        gridView= gridView.findViewById(R.id.enlistedID);
        CustomAdapter adapter= new CustomAdapter(Enlisted_Shop.this,numberWord,numberImage);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"You click"+numberWord[+position],Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
    public class CustomAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private String[] numberWord;
        private int[] numberImage;

        public CustomAdapter(Enlisted_Shop c, String[] numberWord, int[]numberImage){

            this.numberWord=numberWord;
            this.numberImage=numberImage;
        }
        @Override
        public int getCount() {
            return numberWord.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (inflater==null){
                inflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            }
            if (convertView==null){
                convertView= inflater.inflate(R.layout.row_item, null);

            }

            ImageView imageView= convertView.findViewById(R.id.shop_image);
            TextView textView=convertView.findViewById(R.id.shopName);
            imageView.setImageResource(numberImage[position]);
            textView.setText(numberWord[position]);
            return convertView;
        }
    }



}
