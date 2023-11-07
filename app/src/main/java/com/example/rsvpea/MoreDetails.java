package com.example.rsvpea;

import android.content.Intent;
import android.os.Bundle;

import com.example.rsvpea.Models.UploadEventModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rsvpea.databinding.ActivityMoreDetailsBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Timer;

public class MoreDetails extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMoreDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMoreDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
    }
}