package com.esenbaharturkay.instaklon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText emailText;
    EditText passwordText;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth =  FirebaseAuth.getInstance();
        imageView = (ImageView) findViewById(R.id.imageView);
        emailText = (EditText) findViewById(R.id.emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null){
            Intent intent = new Intent(getApplicationContext(),feedActivity.class);
            startActivity(intent);
        }
    }

    public void signin (View view){

         mAuth.signInWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        // İşlem başarılı olursa..


                                        Intent intent = new Intent(getApplicationContext(),feedActivity.class);
                                        startActivity(intent);
                                    }

                                }
                            }).addOnFailureListener(this, new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {  //Başarılı olmazsa
                 Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

             }
         });

    }

    public void signup (View view){

        mAuth.createUserWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) { //Başarılı olursa
                        if (task.isSuccessful()) {
                            // İşlem başarılı olursa..

                            Toast.makeText(MainActivity.this, "User Created !", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(),feedActivity.class);
                            startActivity(intent);
                        }



                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) { //Başarılı olmazsa

                Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
