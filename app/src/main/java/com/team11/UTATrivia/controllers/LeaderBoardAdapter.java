package com.team11.UTATrivia.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team11.UTATrivia.R;
import com.team11.UTATrivia.models.User;
import com.team11.UTATrivia.utils.Utils;

import java.util.ArrayList;


public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<User> adapterData;
    private final LayoutInflater mInflater;


    public LeaderBoardAdapter(Context context, ArrayList<User> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.adapterData = data;
    }

    @NonNull
    @Override
    public LeaderBoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_users, parent, false);
        return new LeaderBoardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LeaderBoardAdapter.ViewHolder holder, int position) {

        holder.nameTextView.setText(adapterData.get(position).getName());
        holder.pointsTextView.setText("" + adapterData.get(position).getPoints());
        Utils.loadImage(context, adapterData.get(position).getUserId(), holder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        if (adapterData != null)
            return adapterData.size();
        else return 0;
    }


    public void setAdapterData(ArrayList<User> adapterData) {
        this.adapterData = adapterData;
    }


    public User getItem(int id) {
        return adapterData.get(id);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Name text view.
         */
        TextView nameTextView;
        /**
         * The Points text view.
         */
        TextView pointsTextView;
        /**
         * The Avatar image view.
         */
        ImageView avatarImageView;



        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            pointsTextView = itemView.findViewById(R.id.pointsTextView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
        }
    }
}
