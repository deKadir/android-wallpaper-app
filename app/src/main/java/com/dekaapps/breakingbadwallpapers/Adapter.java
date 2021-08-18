package com.dekaapps.breakingbadwallpapers;


import android.content.Context;
import android.graphics.Bitmap;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;



import java.util.HashMap;
import java.util.List;

public class Adapter extends PagerAdapter {
    private Context context;
    private List<ImageModel> list;
    Toolbar toolbar;
    HashMap<Integer,Bitmap> loaded;
    boolean isFailed=false;
    boolean toolbarVisible=true;
    ImageView imageView;
    public Adapter(Toolbar bar,Context context, List<ImageModel> list) {
        this.context = context;
        this.list = list;
        loaded=new HashMap<>();
        toolbar=bar;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
         imageView =new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (toolbarVisible){
                toolbar.setVisibility(View.GONE);
                toolbarVisible=false;
            }
            else {
                toolbar.setVisibility(View.VISIBLE);
                toolbarVisible=true;
            }
            }
        });


        Glide.with(context).load(Util.images.get(position).getImageLink()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable  GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
               if (!isFailed){
                   Toast.makeText(context,"No internet connection....", Toast.LENGTH_LONG).show();
               }
               isFailed=true;


                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                loaded.put(position,((BitmapDrawable)resource).getBitmap());
                return false;
            }
        }).into(imageView);

        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }
    public Bitmap getBitmap(int index){
       return loaded.get(index);
    }


}
