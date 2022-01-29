package com.example.fit_club.Adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.fit_club.Home_page;
import com.example.fit_club.Pojo.SliderModel;
import com.example.fit_club.R;
import com.example.fit_club.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class QurekaSliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    protected ArrayList<SliderModel> qsliderArraylist = new ArrayList<>();
    private FirebaseStorage storage;

    public QurekaSliderAdapter(ArrayList<SliderModel> qsliderArraylist, Context context) {
        this.qsliderArraylist = qsliderArraylist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return qsliderArraylist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_slider, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView slider_textView = (TextView) view.findViewById(R.id.slider_textView);
        storage = FirebaseStorage.getInstance();

        Log.d("sliderimageurl10", qsliderArraylist.get(position).getSlider_image());

        StorageReference gsReference = storage.getReferenceFromUrl(qsliderArraylist.get(position).getSlider_image());
        gsReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    String imageurl1 = task.getResult().toString();
                    Log.d("sliderimageurl=========2", imageurl1);
                    Glide.with(context).load(imageurl1).into(imageView);
                }
            }
        });

        slider_textView.setText(qsliderArraylist.get(position).getName());

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}