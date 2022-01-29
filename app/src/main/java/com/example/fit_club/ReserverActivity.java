package com.example.fit_club;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class ReserverActivity extends AppCompatActivity {

    LinearLayout sunday,monday,tuesday,wednesday,thursday,friday,saturday;
    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserver);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Réserver");

        sunday = findViewById(R.id.sunday);
        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.saturday);

        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), ReserverBookActivity.class);
                i.putExtra("day","Dimanche");
                startActivity(i);

            }
        });

        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), ReserverBookActivity.class);
                i.putExtra("day","Lundi");
                startActivity(i);

            }
        });

        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), ReserverBookActivity.class);
                i.putExtra("day","Mardi");
                startActivity(i);

            }
        });

        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), ReserverBookActivity.class);
                i.putExtra("day","Mercredi");
                startActivity(i);

            }
        });

        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), ReserverBookActivity.class);
                i.putExtra("day","Jeudi");
                startActivity(i);

            }
        });

        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), ReserverBookActivity.class);
                i.putExtra("day","Vendredi");
                startActivity(i);

            }
        });

        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), ReserverBookActivity.class);
                i.putExtra("day","Samedi");
                startActivity(i);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return true;
    }
}