package com.example.rsvpea;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rsvpea.databinding.BookingstabBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class BookingsTab extends AppCompatActivity {
    public static ImageButton home;
    public static ImageButton bookings;
    public static ImageButton profile;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference bookingsReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookingstab);
        home = findViewById(R.id.home);
        bookings = findViewById(R.id.myBookings);
        profile = findViewById(R.id.myProfile);
        home.setImageResource(R.drawable.preclickhome);
        profile.setImageResource(R.drawable.profilepreclick);
        bookings.setImageResource(R.drawable.mybookingspostclick);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();
        DatabaseReference userReference = database.getReference("Users").child(userId);
        bookingsReference = userReference.child("bookings");

        ArrayList<MyBookingsTabContent> bookingsContent = new ArrayList<>();
        RecyclerView bookingstimeline = findViewById(R.id.myBookingsRecyclerView);
        bookingstimeline.setLayoutManager(new LinearLayoutManager(this));

        bookingsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot bookingSnapshot : dataSnapshot.getChildren()) {
                    String eventTitle = null;
                    String bookingDate=null;
                    for (DataSnapshot booking : bookingSnapshot.getChildren()) {
                        eventTitle = booking.getKey();
                        bookingDate = booking.getValue(String.class);
                        Log.d("FirebaseData", "Event Title: " + eventTitle + ", Booking Date: " + bookingDate);
                        break;
                    }
                    DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference("Events List");
                    Query query = eventsRef.orderByChild("eventTitle").equalTo(eventTitle);
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                                // You can access the matching events here
                                String eventTitle = eventSnapshot.child("eventTitle").getValue(String.class);
                                String eventDate = eventSnapshot.child("date").getValue(String.class);
                                String eventVenue = eventSnapshot.child("venue").getValue(String.class);
                                String image = eventSnapshot.child("image").getValue(String.class);
                                bookingsContent.add(new MyBookingsTabContent(image, eventTitle,eventDate, eventVenue));
                                Log.d("FirebaseData", "Event Title: " + eventTitle);
                                Log.d("FirebaseData", "Event Date: " + eventDate);
                                Log.d("FirebaseData", "Event Venue: " + eventVenue);
                                break;
                            }
                            MybookingContentAdapter adapter = (new MybookingContentAdapter(BookingsTab.this, bookingsContent));
                            Log.d("FirebaseData", "Data retrieved: " + bookingsContent.size() + " items");
                            bookingstimeline.setAdapter(adapter);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle any errors
                            Log.e("FirebaseData", "Error: " + databaseError.getMessage());
                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BookingsTab.this, "Error ocurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
