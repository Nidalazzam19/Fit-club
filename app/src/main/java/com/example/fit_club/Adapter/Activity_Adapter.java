package com.example.fit_club.Adapter;


import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fit_club.Pojo.ScheduleModel;
import com.example.fit_club.R;


import java.util.ArrayList;


public class Activity_Adapter extends RecyclerView.Adapter<Activity_Adapter.ViewHolder> {

    ArrayList<ScheduleModel> list;
    Context context;
    String day;

    public Activity_Adapter(Context planningActivite, ArrayList<ScheduleModel> list_activity, String day2) {
        context = planningActivite;
        list = list_activity;
        day = day2;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.items_planning, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (day.equalsIgnoreCase("Dimanche")){
            holder.mainBg.setBackground(context.getResources().getDrawable(R.drawable.btn_bg_1));
        }else if (day.equalsIgnoreCase("Lundi")){
            holder.mainBg.setBackground(context.getResources().getDrawable(R.drawable.btn_bg_2));
        }else if (day.equalsIgnoreCase("Mardi")){
            holder.mainBg.setBackground(context.getResources().getDrawable(R.drawable.btn_bg_3));
        }else if (day.equalsIgnoreCase("Mercredi")){
            holder.mainBg.setBackground(context.getResources().getDrawable(R.drawable.btn_bg_4));
        }else if (day.equalsIgnoreCase("Jeudi")){
            holder.mainBg.setBackground(context.getResources().getDrawable(R.drawable.btn_bg_5));
        }else if (day.equalsIgnoreCase("Vendredi")){
            holder.mainBg.setBackground(context.getResources().getDrawable(R.drawable.btn_bg_6));
        }else {
            holder.mainBg.setBackground(context.getResources().getDrawable(R.drawable.btn_bg_7));
        }


//        if (day.equalsIgnoreCase("Sunday")) {
//            holder.item_activity.setTextColor(ContextCompat.getColor(context, R.color.btnbg1_dark));
//        } else if (day.equalsIgnoreCase("Monday")) {
//            holder.item_activity.setTextColor(ContextCompat.getColor(context, R.color.btnbg2_dark));
//        } else if (day.equalsIgnoreCase("Tuesday")) {
//            holder.item_activity.setTextColor(ContextCompat.getColor(context, R.color.btnbg3_dark));
//        } else if (day.equalsIgnoreCase("Wednesday")) {
//            holder.item_activity.setTextColor(ContextCompat.getColor(context, R.color.btnbg4_dark));
//        } else if (day.equalsIgnoreCase("Thursday")) {
//            holder.item_activity.setTextColor(ContextCompat.getColor(context, R.color.btnbg5_dark));
//        } else if (day.equalsIgnoreCase("Friday")) {
//            holder.item_activity.setTextColor(ContextCompat.getColor(context, R.color.btnbg6_dark));
//        } else {
//            holder.item_activity.setTextColor(ContextCompat.getColor(context, R.color.btnbg7_dark));
//        }

        holder.item_activity.setText(list.get(position).getActivity() + "");
        holder.item_day.setText(list.get(position).getDay() + "");
        holder.item_time.setText(list.get(position).getTime() + "");


        String splace = "<b>" + "Place: " + "</b> " + list.get(position).getPlace();
        holder.item_place.setText(Html.fromHtml(splace));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_activity, item_day, item_time, item_place;
        LinearLayout mainBg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_activity = itemView.findViewById(R.id.item_activity);
            item_day = itemView.findViewById(R.id.item_day);
            item_time = itemView.findViewById(R.id.item_time);
            item_place = itemView.findViewById(R.id.item_place);
            mainBg = itemView.findViewById(R.id.mainBg);
        }
    }
}
