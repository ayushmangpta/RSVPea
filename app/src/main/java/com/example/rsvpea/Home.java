package com.example.rsvpea;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rsvpea.Models.UploadEventModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    public static ImageButton home;
    public static ImageButton bookings;
    public static ImageButton profile;
    FirebaseDatabase database;

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
        database=FirebaseDatabase.getInstance();
        database.getReference().child("Events List").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot itemSnapshot : snapshot.getChildren()){
//                    String img = itemSnapshot.child("image").getValue(String.class);
//                    String eventTitle = itemSnapshot.child("eventTitle").getValue(String.class);
                    UploadEventModel temp = itemSnapshot.getValue(UploadEventModel.class);
                    timelineCardContents.add(new TimelineCardContent(temp.getImage(), temp.getEventTitle(), temp));

                }
                RecycleContentAdapter adapter = new RecycleContentAdapter(Home.this, timelineCardContents);
                System.out.println(timelineCardContents);
                timeline.setAdapter(adapter);
                Log.d("FirebaseData", "Data retrieved: " + timelineCardContents.size() + " items");
                Log.d("getChildren", "Children retrieved: " + snapshot.getChildrenCount() + " items");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        home.setOnClickListener(buttonClickListenerHome);
        profile.setOnClickListener(buttonClickListenerProfile);
        bookings.setOnClickListener(buttonClickListenerBookings);
        findViewById(R.id.uploadnewevent).setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, UploadActivity.class);
            Home.this.startActivity(intent);
        });


    }
}
