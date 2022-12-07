package com.team11.UTATrivia.views.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.team11.UTATrivia.R;

import java.util.ArrayList;


public class PlayingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView categoryNameTextView;
    private TextView categoryNumberTextView;

    private ImageView previousImageView;
    private ImageView nextImageView;

    private Button startButton;

    private ArrayList<Integer> categoryNumbers;
    private ArrayList<String> categoryNames;

    private int categoryNumberCounter = 0;
    private int categoryNameCounter = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        categoryNumbers = new ArrayList<>();
        categoryNames = new ArrayList<>();

        categoryNumbers.add(1);
        categoryNumbers.add(2);
        categoryNumbers.add(3);


        // adding the categories
        categoryNames.add("Sports");
        categoryNames.add("History");
        categoryNames.add("Engineering");

        categoryNameTextView = findViewById(R.id.levelNameTextView);
        categoryNumberTextView = findViewById(R.id.levelNumberTextView);

        categoryNameTextView.setText(categoryNames.get(categoryNameCounter));
        categoryNumberTextView.setText("" + categoryNumbers.get(categoryNumberCounter));

        previousImageView = findViewById(R.id.previousImageView);
        nextImageView = findViewById(R.id.nextImageView);

        previousImageView.setOnClickListener(this);
        nextImageView.setOnClickListener(this);

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        //switch between 3 modes
        if (v == previousImageView) {
            if (categoryNumberCounter > 0) {
                categoryNameCounter--;
                categoryNumberCounter--;
                categoryNumberTextView.setText("" + categoryNumbers.get(categoryNumberCounter));
                categoryNameTextView.setText(categoryNames.get(categoryNameCounter));
            }

        }
        if (v == nextImageView) {
            if (categoryNumberCounter < 2) {
                categoryNameCounter++;
                categoryNumberCounter++;
                categoryNumberTextView.setText("" + categoryNumbers.get(categoryNumberCounter));
                categoryNameTextView.setText(categoryNames.get(categoryNameCounter));
            }
        }
        if (v == startButton) {
            finish();
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            intent.putExtra("CATEGORY", (categoryNumberCounter + 1));
            startActivity(intent);
        }
    }
}
