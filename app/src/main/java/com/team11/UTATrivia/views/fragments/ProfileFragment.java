package com.team11.UTATrivia.views.fragments;


import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.team11.UTATrivia.R;
import com.team11.UTATrivia.models.User;
import com.team11.UTATrivia.repository.CurrentDatabase;
import com.team11.UTATrivia.repository.FirebaseDatabaseHelper;
import com.team11.UTATrivia.utils.Utils;
import com.theartofdev.edmodo.cropper.CropImage;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private EditText nameEditText;
    private EditText emailEditText;
    private Button updateProfileButton;

    private ImageView avatarImageView;
    private ImageView uploadImageView;



    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nameEditText = view.findViewById(R.id.et_full_name);
        emailEditText = view.findViewById(R.id.et_email);
        emailEditText.setEnabled(false);

        updateProfileButton = view.findViewById(R.id.btn_update_profile);
        updateProfileButton.setOnClickListener(this);

        avatarImageView = view.findViewById(R.id.iv_avatar);
        avatarImageView.setOnClickListener(this);

        uploadImageView = view.findViewById(R.id.iv_upload);
        uploadImageView.setOnClickListener(this);

        nameEditText.setText(CurrentDatabase.getUser().getName());
        emailEditText.setText(CurrentDatabase.getUser().getEmailAddress());

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == avatarImageView || v == uploadImageView) {
            CropImage.activity()
                    .start(getContext(), this);
        }



        if (v == updateProfileButton) {
            String name = nameEditText.getText().toString().trim();

            if (!name.isEmpty()) {
                User user = CurrentDatabase.getUser();
                user.setName(name);

                CurrentDatabase.setUser(user);
                FirebaseDatabaseHelper.updateUser(user);
                Utils.success(getContext(), "Data updated successfully");
            } else Utils.error(getContext(), "Please fill all the fields to update");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                avatarImageView.setImageURI(resultUri);
                Utils.uploadImage(CurrentDatabase.getUser().getUserId(), resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
