package com.example.fit_club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.fit_club.Adapter.Activity_Adapter;
import com.example.fit_club.Pojo.ScheduleModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PlanningActivity extends AppCompatActivity {

    protected ArrayList<Actualite> mListe;
    protected ArrayList<ScheduleModel> list_activity = new ArrayList<>();
    protected Activity_Adapter adapter;
    protected RecyclerView listView;
    protected MyApplication context;
    protected ActionBar actionBar;
    private DatabaseReference mDatabase;
    String day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        day = intent.getStringExtra("day");

        listView = (RecyclerView) findViewById(R.id.listView);
        //listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setLayoutManager(new GridLayoutManager(this, 2));

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(day);

        context = (MyApplication) this.getApplicationContext();
        initData();

    }


    private void initData() {
        mDatabase = FirebaseDatabase.getInstance().getReference("Schedule");

        mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Iterable<DataSnapshot> interste = task.getResult().getChildren();
                    for (DataSnapshot dataSnapshot : interste) {
                        Log.e("firebase===", "Error getting data " + dataSnapshot.getValue().toString() + "");
                        ScheduleModel activity = new ScheduleModel(dataSnapshot.child("activity").getValue().toString(),
                                dataSnapshot.child("day").getValue().toString(),
                                dataSnapshot.child("time").getValue().toString(),
                                dataSnapshot.child("place").getValue().toString());

                        Log.d("firebase_day", activity.getDay());
                        if (activity.getDay().equalsIgnoreCase(day)){
                            list_activity.add(activity);
                        }

                        //list_activity.add(activity);

                    }
                    adapter = new Activity_Adapter(PlanningActivity.this, list_activity, day);
                    listView.setAdapter(adapter);
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return true;
    }

}