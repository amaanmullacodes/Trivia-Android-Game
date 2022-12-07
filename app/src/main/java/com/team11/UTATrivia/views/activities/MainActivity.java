package com.team11.UTATrivia.views.activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.team11.UTATrivia.R;
import com.team11.UTATrivia.repository.FirebaseDatabaseHelper;
import com.team11.UTATrivia.views.fragments.AboutFragment;
import com.team11.UTATrivia.views.fragments.LeaderboardFragment;
import com.team11.UTATrivia.views.fragments.PlayFragment;
import com.team11.UTATrivia.views.fragments.ProfileFragment;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;


public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    private static final String TAG = "MainActivity";

    private AboutFragment aboutFragment;
    private ProfileFragment profileFragment;
    private PlayFragment playFragment;
    private LeaderboardFragment leaderboardFragment;

    private SmoothBottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        aboutFragment = new AboutFragment();
        profileFragment = new ProfileFragment();
        playFragment = new PlayFragment();
        leaderboardFragment = new LeaderboardFragment();

        initWidgets();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initWidgets() {

        loadFragment(playFragment); // loading play fragment

        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnItemSelectedListener(this);
        bottomBar.setActiveItem(2);
    }

    public void onItemSelect(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = aboutFragment;
                break;

            case 1:
                fragment = profileFragment;
                break;

            case 2:
                fragment = playFragment;
                break;

            case 3:
                fragment = leaderboardFragment;
                break;

            case 4: // if logout is pressed
                FirebaseDatabaseHelper.logoutUser();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;

        }
        loadFragment(fragment);
    }


    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
