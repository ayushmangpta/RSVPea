package com.example.rsvpea;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class BottomNavBar extends AppCompatActivity implements View.OnClickListener {
    private ImageButton bottomNavBarButtons;
    public BottomNavBar(ImageButton bottomNavBarButtons) {
        this.bottomNavBarButtons = bottomNavBarButtons;
        this.bottomNavBarButtons.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int buttonId = view.getId();
        if (buttonId == R.id.home) {
            Home.home.setImageResource(R.drawable.postclickhome);
            Home.profile.setImageResource(R.drawable.profilepreclick);
            Home.bookings.setImageResource(R.drawable.mybookingspreclick);
            Intent intent = new Intent(bottomNavBarButtons.getContext(), Home.class);
            bottomNavBarButtons.getContext().startActivity(intent);
        }
        else if (buttonId == R.id.myBookings) {
            Intent intent = new Intent(bottomNavBarButtons.getContext(), BookingsTab.class);
            bottomNavBarButtons.getContext().startActivity(intent);
        }
        else if (buttonId == R.id.myProfile) {
            Home.home.setImageResource(R.drawable.preclickhome);
            Home.profile.setImageResource(R.drawable.profilepostclick);
            Home.bookings.setImageResource(R.drawable.mybookingspreclick);
            Intent intent = new Intent(bottomNavBarButtons.getContext(), Register.class);
            bottomNavBarButtons.getContext().startActivity(intent);
        }
    }
}
