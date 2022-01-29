package com.example.fit_club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fit_club.Adapter.Activity_Adapter;
import com.example.fit_club.Adapter.QurekaSliderAdapter;
import com.example.fit_club.Pojo.ScheduleModel;
import com.example.fit_club.Pojo.SliderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_page extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private List<SliderItem> sliderItems = new ArrayList<>();
    private Handler slideHandler = new Handler();


    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private FirebaseStorage storage;

    //salam cv
    ViewPager viewPager;
    int currentPage = 0;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        viewPager2 = (ViewPager2) findViewById(R.id.viewPagerImageSlide);

        ImageView user_image = (ImageView) findViewById(R.id.profile_image);
        TextView nom_user = (TextView) findViewById(R.id.nom_user);
        TextView email_user = (TextView) findViewById(R.id.email_user);
        TextView tele_user = (TextView) findViewById(R.id.tele_user);
        CircleImageView profile_image = (CircleImageView) findViewById(R.id.profile_image);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        storage = FirebaseStorage.getInstance();
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User userProfile = snapshot.getValue(User.class);
                userProfile.setProfileImage(snapshot.child("profile_image").getValue().toString());

                if (userProfile != null) {
                    nom_user.setText(userProfile.getPrenom() + " " + userProfile.getNom());
                    email_user.setText(userProfile.getEmail());
                    MyApplication.set_login_email(userProfile.getEmail());
                    tele_user.setText(userProfile.getTele());
                    String url = userProfile.getProfileImage();


                    String imageurl = url.replace("gs://", "https://firebasestorage.googleapis.com/v0/b/");
                    StorageReference gsReference = storage.getReferenceFromUrl(userProfile.getProfileImage());
                    gsReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                String imageurl1 = task.getResult().toString();
                                Log.d("sliderimageurl=========1", imageurl1);
                                Glide.with(Home_page.this).load(imageurl1).placeholder(R.drawable.askal).into(profile_image);
                            }
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home_page.this, "Une erreur", Toast.LENGTH_LONG).show();
            }
        });


        ImageButton planning_btn = (ImageButton) findViewById(R.id.planning_btn);
        planning_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PlanningCategoryActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        ImageButton message_btn = (ImageButton) findViewById(R.id.message_btn);
        message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NotifActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        ImageButton request_btn = (ImageButton) findViewById(R.id.request_btn);
        request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ReserverActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        ImageButton reclam_btn = (ImageButton) findViewById(R.id.reclam_btn);
        reclam_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Reclamation.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        initSliderData();


        sliderItems.add(new SliderItem(R.drawable.image3, "OLYMPIADES UIZ 2022"));
        sliderItems.add(new SliderItem(R.drawable.image2, "JOURNEE-QUEL CHEMIN VERS EMPLOYABILITE"));
        sliderItems.add(new SliderItem(R.drawable.image1, "UIZ organisation"));
        sliderItems.add(new SliderItem(R.drawable.image4, "UIZ organisation"));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        findViewById(R.id.logout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Home_page.this);

                builder.setMessage("Êtes-vous sure que vous voulez déconnecter ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        MyApplication.set_login_email("");
                        startActivity(new Intent(view.getContext(), MainActivity.class));
                        finish();

//                        Intent intent = new Intent(view.getContext(), MainActivity.class);
//                        FirebaseAuth.getInstance().signOut();
//                        startActivity(intent);
//                        finish();
                    }
                });
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        ImageButton actualite = (ImageButton) findViewById(R.id.news_btn);

        actualite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ActualitesActivite.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable, 8000);
            }
        });
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };


    protected ArrayList<SliderModel> qsliderArraylist = new ArrayList<>();
    private DatabaseReference mDatabase;

    private void initSliderData() {
        mDatabase = FirebaseDatabase.getInstance().getReference("Slider");
        mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Iterable<DataSnapshot> interste = task.getResult().getChildren();
                    for (DataSnapshot dataSnapshot : interste) {
                        Log.e("firebase===", "Error getting data " + dataSnapshot.getValue().toString() + "");
                        SliderModel activity = new
                                SliderModel(dataSnapshot.child("name").getValue().toString(),
                                dataSnapshot.child("slider_image").getValue().toString());
                        qsliderArraylist.add(activity);
                    }

                    viewPager = (ViewPager) findViewById(R.id.viewPager);
                    QurekaSliderAdapter viewPagerAdapter = new QurekaSliderAdapter(qsliderArraylist, Home_page.this);
                    viewPager.setAdapter(viewPagerAdapter);
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    final Handler handler = new Handler();
                    final Runnable Update = new Runnable() {
                        public void run() {
                            if (currentPage == 7 - 1) {
                                currentPage = 0;
                            }
                            viewPager.setCurrentItem(currentPage++, true);
                        }
                    };

                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(Update);
                        }
                    }, 500, 3000);

                }
            }
        });

    }


}