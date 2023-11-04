package com.example.rsvpea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/{
   // public static ImageButton home;
    //public static ImageButton bookings;
    //public static ImageButton profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, Home.class);
        this.startActivity(intent);
//        home = findViewById(R.id.home);
//        bookings = findViewById(R.id.myBookings);
//        profile = findViewById(R.id.myProfile);
//        BottomNavBar buttonClickListenerHome = new BottomNavBar(home);
//        BottomNavBar buttonClickListenerBookings = new BottomNavBar(bookings);
//        BottomNavBar buttonClickListenerProfile = new BottomNavBar(profile);
//
//        ArrayList<TimelineCardContent> timelineCardContents = new ArrayList<>();
//
//        RecyclerView timeline = findViewById(R.id.timelineRecyclerView);
//        timeline.setLayoutManager(new LinearLayoutManager(this));
//
//        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
//        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
//        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
//        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
//        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
//        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
//        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
//
//        RecycleContentAdapter adapter = new RecycleContentAdapter(this, timelineCardContents);
//        timeline.setAdapter(adapter);
//        home.setOnClickListener(buttonClickListenerHome);
//        profile.setOnClickListener(buttonClickListenerProfile);
//        bookings.setOnClickListener(buttonClickListenerBookings);
    }

    /*@Override
    public void onClick(View view) {
        if(view==findViewById(R.id.moreDetails)){
            Intent intent = new Intent(this, MoreDetails.class);
            this.startActivity(intent);
        }
    }*/
}
