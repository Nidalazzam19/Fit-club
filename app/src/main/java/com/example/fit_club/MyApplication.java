package com.example.fit_club;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;

public class MyApplication extends Application {

    private ArrayList<Actualite> actualites;

    static SharedPreferences preferences;
    static SharedPreferences.Editor prefEditor;

    public  ArrayList<Actualite> getActualites() {
        return actualites;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        //actualites=initData();

        preferences = getSharedPreferences("Tijori", MODE_PRIVATE);
        prefEditor = preferences.edit();

        //MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }


    public static void set_login_email(String login_id) {
        prefEditor.putString("login_email", login_id).commit();
    }

    public static String get_login_email() {
        return preferences.getString("login_email", "");
    }


//    public ArrayList<Actualite> initData() {
//
//        Resources res = getResources();
//
//        final String[] intitule = res.getStringArray(R.array.intitules);
//        final String[] date = res.getStringArray(R.array.dates);
//        TypedArray images = res.obtainTypedArray(R.array.idimages);
//
//        ArrayList<Actualite> myListe = new ArrayList<>();
//
//        for(int i=0; i <intitule.length; i++ ){
//            myListe.add(new Actualite(intitule[i],date[i],images.getResourceId(i, 0)));
//        }
//
//        return myListe;
//
//    }

}