package com.team11.UTATrivia.views.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.team11.UTATrivia.R;
import com.team11.UTATrivia.repository.CurrentDatabase;
import com.team11.UTATrivia.utils.Utils;
import com.team11.UTATrivia.views.activities.PlayingActivity;


public class PlayFragment extends Fragment implements View.OnClickListener {

    private TextView levelTextView;
    private ImageView avatarImageView;
    private ImageView onlineImageView;

    private TextView nameTextView;
    private TextView addressTextView;
    private TextView pointsTextView;

    private ImageView newGameImageView;



    public PlayFragment() {

    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_play, container, false);

        levelTextView = v.findViewById(R.id.levelTextView);
        avatarImageView = v.findViewById(R.id.avatarImageView);
        onlineImageView = v.findViewById(R.id.onlineImageView);


        nameTextView = v.findViewById(R.id.nameTextView);
        addressTextView = v.findViewById(R.id.addressTextView);
        pointsTextView = v.findViewById(R.id.pointsTextView);

        // just updating the views
        if (CurrentDatabase.getUser() != null) {
            nameTextView.setText(CurrentDatabase.getUser().getName());
            levelTextView.setText(CurrentDatabase.getUser().getLevel() + "");
            Utils.loadImage(getContext(), CurrentDatabase.getUser().getUserId(), avatarImageView);
            pointsTextView.setText(CurrentDatabase.getUser().getPoints() + "");

            if (!CurrentDatabase.getUser().getOnlineStatus().equalsIgnoreCase("ONLINE")) {
                onlineImageView.setVisibility(View.INVISIBLE);
            }
        }

        newGameImageView = v.findViewById(R.id.newGameImageView);
        newGameImageView.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == newGameImageView) {
            startActivity(new Intent(getContext(), PlayingActivity.class));
        }
    }
}
