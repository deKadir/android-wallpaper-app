package com.dekaapps.breakingbadwallpapers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class ImageGallery extends Fragment implements ImageAdapter.OnImageListener {
    private View view;
    private RecyclerView recyclerView;


    private ImageAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            if (view==null){

                view= inflater.inflate(R.layout.fragment_image_gallery, container, false);
                viewSettings();
            return view ;

            }



        return view;
    }
    private void viewSettings(){
         AdRequest request= new AdRequest.Builder().build();
        AdView adView=view.findViewById(R.id.adView);
        adView.loadAd(request);
        recyclerView=view.findViewById(R.id.imagesRecyclerView);
        if (Util.images.isEmpty()) {

            Util.images.add(new ImageModel("https://i.hizliresim.com/knq6y7r.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/oazgyc9.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/b1mibnt.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/knv6sch.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/p25xcwf.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/3gixvlq.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/2sfovrq.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/sqi9uky.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/9ej8lai.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/j25kfka.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/py66ccd.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/p7ghfid.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/barywqh.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/hbm0kca.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/5ig1374.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/406txc7.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/cj4g6bo.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/d02gxcz.png"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/beu5uq8.png"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/5ile23f.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/6tfwo5d.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/3km0zuo.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/97duv8j.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/moa3qcx.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/g5r0rvi.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/f9j8ssq.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/6h4a85t.png"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/my43ta7.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/8061p80.png"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/stdfs92.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/mug9kjq.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/48myol8.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/4brgodi.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/svrf06r.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/isiof62.png"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/alby96r.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/tfmzoea.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/q2vge6f.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/4yqwpgb.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/an3b4j1.png"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/nrbia24.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/gvuohw5.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/qlmkyw3.png"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/cccpsnl.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/knd68ja.png"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/bpv4snx.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/cyr2b8l.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/n9217pa.png"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/sgny04p.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/6bu7b2l.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/6bc6opt.jpg"));
            Util.images.add(new ImageModel("https://i.hizliresim.com/8eh6gra.jpg"));






        }
        adapter=new ImageAdapter(Util.images,getContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

    }

    @Override
    public void onImageClick(int position) {
        Util.index=position;
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.container,new ImageFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}