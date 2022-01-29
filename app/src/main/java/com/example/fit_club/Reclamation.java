package com.example.fit_club;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fit_club.R;

public class Reclamation extends AppCompatActivity {
    private EditText message;
   private  Button Send;
    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Réclamer");

        message=findViewById(R.id.txtMessage);
        Send=findViewById(R.id.btnSend);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }else{
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                }

            }
            }
        });

    }
    private void sendSMS(){
        String SMS=message.getText().toString().trim();
       try {
           SmsManager smsManager=SmsManager.getDefault();
           smsManager.sendTextMessage("0708981209",null,SMS,null,null);
           Toast.makeText(this,"Réclamation envoyer",Toast.LENGTH_LONG).show();
       }catch (Exception e){
           e.printStackTrace();
           Toast.makeText(this,"Erreur",Toast.LENGTH_LONG).show();
       }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return true;
    }
}
