package com.example.daamdekhi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    Context mContext;
    private List<product> mData = new ArrayList<>();

    public RecyclerViewAdapter(Context context, List<product> productlist) {
        this.mContext = context;
        this.mData = productlist;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

      /*  View view;
        LayoutInflater mInflater= LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.cardveiw_item_book,viewGroup,false);

        return new MyViewHolder(view);*/

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardveiw_item_book, viewGroup, false);
        RecyclerViewAdapter.MyViewHolder hold = new RecyclerViewAdapter.MyViewHolder(view);


        return (hold);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.tv_book_title.setText(mData.get(i).getTitle());
        //myViewHolder.img_book_thumbnail.setImageResource(mData.get(i).getThumbnail());
        Glide.with(mContext)
                .asBitmap()
                .load(mData.get(i).getThumbnail())
                .into(myViewHolder.img_book_thumbnail);


        //set click listener
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*

                Intent intent= new Intent(mContext,Book_Activity.class);

                //passing data to the book activity
                intent.putExtra("Title",mData.get(i).getTitle());
                intent.putExtra("Description",mData.get(i).getDescription());
                intent.putExtra("Thumbnail",mData.get(i).getThumbnail());


                // start the activity
                mContext.startActivity(intent);
*/

            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView;


        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            tv_book_title=(TextView)itemView.findViewById(R.id.book_title_id);
            img_book_thumbnail=(ImageView)itemView.findViewById(R.id.book_img_id);
            cardView=(CardView) itemView.findViewById(R.id.cardview_id);

            img_book_thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,ProductActivity.class);

                    //passing data to the book activity
                    intent.putExtra("Title",mData.get(getAdapterPosition()).getTitle());
                    intent.putExtra("Category",mData.get(getAdapterPosition()).getCategory());
                    intent.putExtra("Description",mData.get(getAdapterPosition()).getDescription());
                    intent.putExtra("Thumbnail",mData.get(getAdapterPosition()).getThumbnail());

                    // start the activity
                    //mContext.startActivity(intent);

                    mContext.startActivity(intent);

                }
            });

        }
    }
}
