package com.example.fit_club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class forgot_password extends AppCompatActivity {

    private ProgressBar progressBar ;
    private FirebaseAuth mAuth ;
    private String email_string;
    private EditText email;
    private Button cancel;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        cancel = (Button)findViewById(R.id.forgotEmailCancel);
        ok = (Button)findViewById(R.id.forgotEmailOk);

        email = (EditText)findViewById(R.id.Email);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(view.getContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void resetPassword() {
        email_string=email.getText().toString().trim();

        if(email_string.isEmpty()){
            email.setError("Veuillez saisir votre email !");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email_string).matches()){
            email.setError("Veuillez saisir un email valide !");
            email.requestFocus();
            return;
        }

        System.out.println(progressBar);

        mAuth.sendPasswordResetEmail(email_string).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   progressBar.setVisibility(View.GONE);
                   Toast.makeText(forgot_password.this,"Vérifier votre email pour récupérer le mot de passe",Toast.LENGTH_LONG).show();
               }else{
                   Toast.makeText(forgot_password.this,"Réinitialisation échouée",Toast.LENGTH_LONG).show();
                   progressBar.setVisibility(View.GONE);
               }
            }
        });



    }

}