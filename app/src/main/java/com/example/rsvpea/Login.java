package com.example.rsvpea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rsvpea.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Loging in..");
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.emailEditTextLogin.getText().toString(), binding.passwordEditTextLogin.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                 progressDialog.dismiss();
                                 if(task.isSuccessful()){
                                     Intent intent = new Intent(Login.this, MainActivity.class);
                                     Login.this.startActivity(intent);
                                 }
                                 else {
                                     Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                 }
                            }
                        });
            }
        });
        if(auth.getCurrentUser()!=null){
            Intent intent = new Intent(Login.this, MainActivity.class);
            Login.this.startActivity(intent);
        }
    }
}