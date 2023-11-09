package com.example.rsvpea;

import android.content.Intent;
import android.os.Bundle;

import com.example.rsvpea.Models.UploadEventModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rsvpea.databinding.ActivityMoreDetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class MoreDetails extends AppCompatActivity {

    private ActivityMoreDetailsBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoreDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String img = intent.getStringExtra("img");
        UploadEventModel eventModel = intent.getParcelableExtra("eventModel");

        Picasso.get().load(img).into(binding.eventImageInsideCardView);
        binding.titleMoreDetails.setText(title);
        binding.venueNameText.setText(eventModel.getVenue());
        binding.descriptionText.setText(eventModel.getDesc());

        binding.backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreDetails.this, MainActivity.class);
                MoreDetails.this.startActivity(intent);
            }
        });

        binding.booknowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference userReference = database.getReference("Users").child(userId);
                DatabaseReference bookingsReference = userReference.child("bookings");

                Map<String, Object> bookingData = new HashMap<>();
                bookingData.put(title, eventModel.getDate());

                bookingsReference.push().setValue(bookingData, (databaseError, databaseReference) -> {
                    if (databaseError == null) {
                        // Booking data added successfully
                        Toast.makeText(MoreDetails.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle the error
                        Toast.makeText(MoreDetails.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}