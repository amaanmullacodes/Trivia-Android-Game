package com.team11.UTATrivia.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team11.UTATrivia.R;
import com.team11.UTATrivia.controllers.LeaderBoardAdapter;
import com.team11.UTATrivia.models.User;
import com.team11.UTATrivia.repository.FirebaseDatabaseHelper;

import java.util.ArrayList;


public class LeaderboardFragment extends Fragment {

    private RecyclerView medsRecyclerView;

    private LeaderBoardAdapter leaderBoardAdapter;

    private ArrayList<User> users;


    public LeaderboardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        users = FirebaseDatabaseHelper.getAllUsers();



        if (users != null && !users.isEmpty()) {
            users.sort((o1, o2) -> o2.getPoints().compareTo(o1.getPoints()));
            medsRecyclerView = v.findViewById(R.id.rv_medications);
            medsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            leaderBoardAdapter = new LeaderBoardAdapter(getContext(), users);
            medsRecyclerView.setAdapter(leaderBoardAdapter);
        }
        return v;
    }
}
