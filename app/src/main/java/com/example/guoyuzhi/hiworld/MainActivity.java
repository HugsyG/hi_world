package com.example.guoyuzhi.hiworld;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText inputNameEditText = (EditText)findViewById(R.id.inputName);
                EditText inputPasswordEditText = (EditText)findViewById(R.id.inputPassword);
                String usrInputName = inputNameEditText.getText().toString();
                String usrInputPassword = inputPasswordEditText.getText().toString();
                System.out.println(usrInputName);
                System.out.println(usrInputPassword);
                signIn(usrInputName, usrInputPassword);
            }
        });
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener < AuthResult > () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            setContentView(R.layout.activity_successful);
                        } else {
                            // If sign in fails, display a message to the user.
                            setContentView(R.layout.activity_failed);
                        }
                    }
                });
    }
}
