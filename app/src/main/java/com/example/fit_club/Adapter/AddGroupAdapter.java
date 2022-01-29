package com.example.fit_club.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fit_club.Pojo.TrainnerModel;
import com.example.fit_club.R;

import java.util.ArrayList;

public class AddGroupAdapter extends BaseAdapter {

    Context context;
    protected ArrayList<TrainnerModel> trainnerarraylist = new ArrayList<>();
    LayoutInflater layoutInflater;

    public AddGroupAdapter(Context context, ArrayList<TrainnerModel> trainnerarraylist) {
        this.context = context;
        this.trainnerarraylist = trainnerarraylist;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return trainnerarraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("WrongConstant")
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View view1 = layoutInflater.inflate(R.layout.layout_add_group, viewGroup, false);
        final TextView textView = view1.findViewById(R.id.buss_cat_tv);

        textView.setText(trainnerarraylist.get(i).getName());

        return view1;
    }

}
