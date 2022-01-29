package com.example.fit_club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fit_club.Adapter.Activity_Adapter;
import com.example.fit_club.Adapter.AddGroupAdapter;
import com.example.fit_club.Pojo.Booking;
import com.example.fit_club.Pojo.ScheduleModel;
import com.example.fit_club.Pojo.TrainnerModel;
import com.example.fit_club.Utils.RandomString;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ReserverBookActivity extends AppCompatActivity {

    EditText et_selecttrailer;
    LinearLayout timeslot1, timeslot2, timeslot3, timeslot4, timeslot5, timeslot6;
    ImageView iv1, iv2, iv3, iv4, iv5, iv6;
    TextView btnConfirmSlot;
    private DatabaseReference mDatabase;
    private DatabaseReference bookingDatabase;
    private DatabaseReference userDatabase;
    private FirebaseUser user;
    private String userName;
    private String userEmail;
    protected ActionBar actionBar;
    String name, email, time, tele, trannername;
    String day;

    String getTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserver_book);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        day = intent.getStringExtra("day");

        actionBar.setTitle(day);

        bookingDatabase = FirebaseDatabase.getInstance().getReference();
        userDatabase = FirebaseDatabase.getInstance().getReference("Users");


        user = FirebaseAuth.getInstance().getCurrentUser();
        userDatabase.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User userProfile = snapshot.getValue(User.class);
                userProfile.setProfileImage(snapshot.child("profile_image").getValue().toString());

                if (userProfile != null) {
                    name = userProfile.getPrenom() + " " + userProfile.getNom();
                    email = userProfile.getEmail();
                    tele = userProfile.getTele();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReserverBookActivity.this, "Une erreur", Toast.LENGTH_LONG).show();
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        et_selecttrailer = findViewById(R.id.et_selecttrailer);
        timeslot1 = findViewById(R.id.timeslot1);
        timeslot2 = findViewById(R.id.timeslot2);
        timeslot3 = findViewById(R.id.timeslot3);
        timeslot4 = findViewById(R.id.timeslot4);
        timeslot5 = findViewById(R.id.timeslot5);
        timeslot6 = findViewById(R.id.timeslot6);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv6 = findViewById(R.id.iv6);
        btnConfirmSlot = findViewById(R.id.btnConfirmSlot);

        et_selecttrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialod_AddGroupCategory();

            }
        });

        timeslot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.GONE);
                iv4.setVisibility(View.GONE);
                iv5.setVisibility(View.GONE);
                iv6.setVisibility(View.GONE);

            }
        });

        timeslot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv1.setVisibility(View.GONE);
                iv2.setVisibility(View.VISIBLE);
                iv3.setVisibility(View.GONE);
                iv4.setVisibility(View.GONE);
                iv5.setVisibility(View.GONE);
                iv6.setVisibility(View.GONE);

            }
        });

        timeslot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv1.setVisibility(View.GONE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.VISIBLE);
                iv4.setVisibility(View.GONE);
                iv5.setVisibility(View.GONE);
                iv6.setVisibility(View.GONE);

            }
        });


        timeslot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv1.setVisibility(View.GONE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.GONE);
                iv4.setVisibility(View.VISIBLE);
                iv5.setVisibility(View.GONE);
                iv6.setVisibility(View.GONE);

            }
        });

        timeslot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv1.setVisibility(View.GONE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.GONE);
                iv4.setVisibility(View.GONE);
                iv5.setVisibility(View.VISIBLE);
                iv6.setVisibility(View.GONE);

            }
        });

        timeslot6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv1.setVisibility(View.GONE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.GONE);
                iv4.setVisibility(View.GONE);
                iv5.setVisibility(View.GONE);
                iv6.setVisibility(View.VISIBLE);

            }
        });

        btnConfirmSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_selecttrailer.getText().toString().trim().equals("")) {
                    et_selecttrailer.setError("Sélectionnez Formateur");
                } else if (iv1.getVisibility() == View.GONE && iv2.getVisibility() == View.GONE && iv3.getVisibility() == View.GONE &&
                        iv4.getVisibility() == View.GONE && iv5.getVisibility() == View.GONE && iv6.getVisibility() == View.GONE) {
                    Toast.makeText(ReserverBookActivity.this, "Sélectionnez votre créneau horaire", Toast.LENGTH_SHORT).show();
                } else {


                    if (iv1.getVisibility() == View.VISIBLE) {
                        getTime = "06:00 am";
                    } else if (iv2.getVisibility() == View.VISIBLE) {
                        getTime = "07:30 am";
                    } else if (iv3.getVisibility() == View.VISIBLE) {
                        getTime = "09:00 am";
                    } else if (iv4.getVisibility() == View.VISIBLE) {
                        getTime = "12:30 am";
                    } else if (iv5.getVisibility() == View.VISIBLE) {
                        getTime = "05:30 pm";
                    } else if (iv6.getVisibility() == View.VISIBLE) {
                        getTime = "07:30 pm";
                    }
                    Log.d("RandomStringData", getTime);

                    RandomString session = new RandomString();
                    Log.d("RandomStringData", session.nextString());

                    Booking booking = new Booking(name, email, getTime, tele, et_selecttrailer.getText().toString());
                    bookingDatabase.child("Booking").child(user.getUid()).child(session.nextString()).setValue(booking);

                    Toast.makeText(ReserverBookActivity.this, "Réserver avec succès", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    ListView AddGroupList;

    public void Dialod_AddGroupCategory() {
        final Dialog dialog = new Dialog(ReserverBookActivity.this, android.R.style.Theme_Holo_Light_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.business_category_dialog);

        AddGroupList = dialog.findViewById(R.id.UserProfile_list);

        trainnerCategory();

        AddGroupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pos = adapterView.getItemAtPosition(i).toString();

                et_selecttrailer.setText(trainnerarraylist.get(i).getName());
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    protected ArrayList<TrainnerModel> trainnerarraylist = new ArrayList<>();

    private void trainnerCategory() {
        trainnerarraylist.clear();

        mDatabase = FirebaseDatabase.getInstance().getReference("Members");

        mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Iterable<DataSnapshot> interste = task.getResult().getChildren();
                    for (DataSnapshot dataSnapshot : interste) {
                        Log.e("firebase===", "Error getting data " + dataSnapshot.getValue().toString() + "");


                        TrainnerModel trainnerModel = new TrainnerModel(dataSnapshot.child("email").getValue().toString(),
                                dataSnapshot.child("name").getValue().toString(),
                                dataSnapshot.child("tele").getValue().toString());

                        trainnerarraylist.add(trainnerModel);
                    }

                    AddGroupAdapter addGroupAdapter = new
                            AddGroupAdapter(ReserverBookActivity.this, trainnerarraylist);
                    AddGroupList.setAdapter(addGroupAdapter);

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