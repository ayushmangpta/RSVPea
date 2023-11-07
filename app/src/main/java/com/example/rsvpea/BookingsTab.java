package com.example.rsvpea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookingsTab extends AppCompatActivity{
    public static ImageButton home;
    public static ImageButton bookings;
    public static ImageButton profile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookingstab);
        home = findViewById(R.id.home);
        bookings = findViewById(R.id.myBookings);
        profile = findViewById(R.id.myProfile);
        home.setImageResource(R.drawable.preclickhome);
        profile.setImageResource(R.drawable.profilepreclick);
        bookings.setImageResource(R.drawable.mybookingspostclick);
        ArrayList<MyBookingsTabContent> bookingscontent= new ArrayList<>();
        RecyclerView bookingstimeline = findViewById(R.id.myBookingsRecyclerView);
        bookingstimeline.setLayoutManager(new LinearLayoutManager(this));
        bookingscontent.add(new MyBookingsTabContent(R.drawable.sample, "title Example", "27 October 2023 4:40pm", "Ramdas Pai"));
        MybookingContentAdapter adapter1 = new MybookingContentAdapter(this, bookingscontent);
        bookingstimeline.setAdapter(adapter1);

    }
}
