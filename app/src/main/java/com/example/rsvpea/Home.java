package com.example.rsvpea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    public static ImageButton home;
    public static ImageButton bookings;
    public static ImageButton profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home = findViewById(R.id.home);
        bookings = findViewById(R.id.myBookings);
        profile = findViewById(R.id.myProfile);
        BottomNavBar buttonClickListenerHome = new BottomNavBar(home);
        BottomNavBar buttonClickListenerBookings = new BottomNavBar(bookings);
        BottomNavBar buttonClickListenerProfile = new BottomNavBar(profile);

        ArrayList<TimelineCardContent> timelineCardContents = new ArrayList<>();

        RecyclerView timeline = findViewById(R.id.timelineRecyclerView);
        timeline.setLayoutManager(new LinearLayoutManager(this));

        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));
        timelineCardContents.add(new TimelineCardContent(R.drawable.sample, "title example"));

        RecycleContentAdapter adapter = new RecycleContentAdapter(this, timelineCardContents);
        timeline.setAdapter(adapter);
        home.setOnClickListener(buttonClickListenerHome);
        profile.setOnClickListener(buttonClickListenerProfile);
        bookings.setOnClickListener(buttonClickListenerBookings);
        findViewById(R.id.uploadnewevent).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, UploadActivity.class);
                Home.this.startActivity(intent);
            }
        });

    }
}
