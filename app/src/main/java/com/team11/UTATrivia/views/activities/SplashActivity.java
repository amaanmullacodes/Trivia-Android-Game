package com.team11.UTATrivia.views.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.team11.UTATrivia.R;
import com.team11.UTATrivia.repository.CurrentDatabase;
import com.team11.UTATrivia.repository.FirebaseDatabaseHelper;
import com.team11.UTATrivia.utils.NetworkStateReceiver;
import com.team11.UTATrivia.utils.QuizBank;
import com.team11.UTATrivia.utils.Utils;


public class SplashActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener, View.OnClickListener {

    private static final String TAG = "SplashActivity";
    private Snackbar snackbar;
    private NetworkStateReceiver networkStateReceiver;

    private boolean opened = true;
    private Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getStartedButton = findViewById(R.id.btn_get_started);
        getStartedButton.setOnClickListener(this);

        View parentLayout = findViewById(android.R.id.content);
        snackbar = Snackbar.make(parentLayout, "Not connected to Internet! Please turn on wifi or mobile data", Snackbar.LENGTH_INDEFINITE);

        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);


        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (networkStateReceiver != null)
            unregisterReceiver(networkStateReceiver);
    }

    private void splashScreenTime() {

        Log.e(TAG, "In Splash Screen");

        new Handler().postDelayed(() -> {

            String email = Utils.getDataFromSharedPrefs(getApplicationContext(), "email");
            if (!email.isEmpty()) {
                CurrentDatabase.setUser(FirebaseDatabaseHelper.getUserByEmail(email)); // check if email is stored then login the user
                if (CurrentDatabase.getUser() != null)
                    startActivity(new Intent(this, MainActivity.class)); // if successful login then go to main activity
                else
                    startActivity(new Intent(this, LoginActivity.class)); // if it fails then go to login activity
            } else
                startActivity(new Intent(this, LoginActivity.class));// if the email is not even stored then as well go to login
            finish();

        }, 3000); // wait for 3 seconds so data can be loaded
    }


    @Override
    public void networkAvailable() {
        snackbar.dismiss();
        if (opened) {
            new FirebaseDatabaseHelper();
            new QuizBank();
            if (!Utils.isFirstLogin(getApplicationContext())) {
                getStartedButton.setEnabled(false);
                splashScreenTime();
            }
            opened = false;
        }
    }

    @Override
    public void networkUnavailable() {
        snackbar.show();
    }

    //this handles the click listener
    @Override
    public void onClick(View v) {
        if (v == getStartedButton) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }
}
