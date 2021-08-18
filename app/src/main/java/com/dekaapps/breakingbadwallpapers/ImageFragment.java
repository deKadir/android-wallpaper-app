package com.dekaapps.breakingbadwallpapers;

import android.annotation.SuppressLint;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;



import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public class ImageFragment extends Fragment {
    View view;
    Adapter adapter;
    ViewPager pager;
    Bitmap bitmap;
    private InterstitialAd mInterstitialAd;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            view= inflater.inflate(R.layout.fragment_image, container, false);

            setImage();



        return view;
    }
    public void setImage()  {

        Toolbar toolbar=view.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        adapter=new Adapter(toolbar,getContext(),Util.images);
        pager=view.findViewById(R.id.viewPager);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(3);
        pager.setCurrentItem(Util.index);
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(getContext(),"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.show(getActivity());

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error

                        mInterstitialAd = null;
                    }
                });

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);







    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.image_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);

    }
    @SuppressLint("WrongThread")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            FragmentManager manager=getFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.container,new ImageGallery());
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else if(item.getItemId()==R.id.download){
                    bitmap=adapter.getBitmap(pager.getCurrentItem());
                    File sdCard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdCard.getAbsolutePath()+"/DCIM");
                    dir.mkdirs();
                    String fileName = String.format("%d.jpg", System.currentTimeMillis());
                    File outFile = new File(dir, fileName);

                    try {



                        FileOutputStream outStream;
                        outStream = new FileOutputStream(outFile);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);

                        outStream.flush();
                        outStream.close();
                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        intent.setData(Uri.fromFile(outFile));
                        getActivity().sendBroadcast(intent);
                        Toast.makeText(getContext(),"Downloaded successfully",Toast.LENGTH_LONG).show();
                    }  catch (Exception e){
                        e.printStackTrace();

                        Toast.makeText(getContext(),"Couldn't downloaded "+e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                }


        else if(item.getItemId()==R.id.setAsBackGround){


            WallpaperManager myWallpaperManager
                    = WallpaperManager.getInstance(getContext());
            try {
                bitmap=adapter.getBitmap(pager.getCurrentItem());
                myWallpaperManager.setBitmap(bitmap);
                Toast.makeText(getContext(),"Wallpaper changed",Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                // TODO Auto-generated catch block
               e.printStackTrace();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}