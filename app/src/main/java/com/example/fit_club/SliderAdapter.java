package com.example.fit_club;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.logging.Handler;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{

    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;

    public SliderAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slide_item_container,parent,false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
    holder.setItem(sliderItems.get(position));
    if(position == sliderItems.size()-2){
        viewPager2.post(runnable);
    }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
            title = itemView.findViewById(R.id.title);
        }

        void setItem(SliderItem sliderItem){
            imageView.setImageResource(sliderItem.getImage());
            title.setText(sliderItem.getTitle());
        }

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };


}
