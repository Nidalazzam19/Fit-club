package com.example.fit_club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActualitesActivite extends AppCompatActivity {

    protected ArrayList<Actualite> mListe;
    protected ActualiteAdapterRV adapter;
    protected ListView listView;
    protected ActionBar actionBar;
    protected DatabaseReference databaseReference;
    protected MyApplication context;
    protected RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualites_activite);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mListe = new ArrayList<>();
        actionBar = getSupportActionBar();
        context = (MyApplication) this.getApplicationContext();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Actualités");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Actualités");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    String titre = data.child("titre").getValue().toString();
                    String date = data.child("date").getValue().toString();
                    String description = data.child("description").getValue().toString();
                    String id = data.child("id").getValue().toString();

                    Actualite act = new Actualite(titre, date,description);
//                    act.setDescription(description);
//                    act.setIdImage(R.drawable.new_a);
                    mListe.add(act);
                }
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActualitesActivite.this);
                adapter = new ActualiteAdapterRV(mListe);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem my_menu = menu.findItem(R.id.search_actualite);
        SearchView searchView = (SearchView) my_menu.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i = new Intent(ActualitesActivite.this, Home_page.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

        return true;
    }

}