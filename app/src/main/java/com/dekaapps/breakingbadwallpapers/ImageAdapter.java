package com.dekaapps.breakingbadwallpapers;


import android.content.Context;

import android.graphics.drawable.Drawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.snackbar.Snackbar;


import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder>{
    private final List<ImageModel> images;
    private final OnImageListener listener;
    Context context;
    View view;
    Boolean alreadyFailed=false;
    public ImageAdapter(List<ImageModel> list,Context context,OnImageListener listener){
        this.images=list;
        this.context=context;
        this.listener=listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycler_item,parent,false);

        return new MyViewHolder(view,listener);
    }
    //github
    @Override
    public void onBindViewHolder( ImageAdapter.MyViewHolder holder, int position) {

        Glide.with(context).load(images.get(position).getImageLink()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed( GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                if (!alreadyFailed){
                    Snackbar snackbar = Snackbar.make(view,"No internet connection turn on the internet and restart app...",Snackbar.LENGTH_LONG);
                    snackbar.show();

                    snackbar.setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    alreadyFailed=true;
                }


                return false;

            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.bar.setVisibility(View.GONE);
                return false;
            }
        })
                .into(holder.image);




    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        OnImageListener imageListener;
        ProgressBar bar;
        public MyViewHolder(@NonNull View itemView,OnImageListener imageListener) {
            super(itemView);
            this.imageListener=imageListener;
            image=itemView.findViewById(R.id.row_image);
            bar=itemView.findViewById(R.id.progressBar);
            bar.setVisibility(View.VISIBLE);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        imageListener.onImageClick(getAdapterPosition());
        }
    }
    public interface OnImageListener{
        void onImageClick(int position);
    }


}
