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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email ;
    private EditText paswword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String login = MyApplication.get_login_email();
        if (!login.equals("")) {
            startActivity(new Intent(MainActivity.this,Home_page.class));
            finish();
        }

        email = (EditText)findViewById(R.id.email);
        paswword= (EditText)findViewById(R.id.password);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        Button loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        TextView forgotPassword = (TextView) findViewById(R.id.forgotPass);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), forgot_password.class);
                startActivity(i);
            }
        });
    }


    private void userLogin() {
        String email_txt = email.getText().toString().trim();
        String paswword_txt = paswword.getText().toString().trim();

        if(email_txt.isEmpty()){

            email.setError("Veuillez remplir le champ email !");
            email.requestFocus();
            return;

        }


        if(paswword_txt.isEmpty()){
            paswword.setError("Veuillez remplir le champ password !");
            paswword.requestFocus();
            return;

        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email_txt).matches()){
            email.setError("Veuillez saisir un email valide !");
            email.requestFocus();
            return;

        }

        if(paswword_txt.length()<6){

            paswword.setError("Le mot de passe doit comporte au moins 6 caractéres");
            paswword.requestFocus();
            return;

        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email_txt,paswword_txt).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(MainActivity.this,Home_page.class));
                            finish();
                        }else{
                            Toast.makeText(MainActivity.this,"Connexion échouée",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }
        );
    }





}