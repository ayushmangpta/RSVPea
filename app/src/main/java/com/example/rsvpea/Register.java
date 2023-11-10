package com.example.rsvpea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rsvpea.Models.Users;
import com.example.rsvpea.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth =FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(Register.this);
        progressDialog.setTitle("Create Account");
        progressDialog.setMessage("Creating Account");
        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.emailEditText.getText().toString();
                String password = binding.passwordEditText.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Register.this, "Email and password are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.show();
                auth.createUserWithEmailAndPassword(binding.emailEditText.getText().toString(), binding.passwordEditText.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                Users user = new Users(binding.nameEditText.getText().toString(), binding.emailEditText.getText().toString(),
                                        binding.passwordEditText.getText().toString());
                                String id = task.getResult().getUser().getUid();
                                database.getReference().child("Users").child(id).setValue(user);
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(Register.this, MainActivity.class);
                                    Register.this.startActivity(intent);
                                    Toast.makeText(Register.this,"User Registered Succesfully", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        binding.alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                Register.this.startActivity(intent);
            }
        });

    }
}