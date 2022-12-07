package com.team11.UTATrivia.views.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.team11.UTATrivia.R;
import com.team11.UTATrivia.models.User;
import com.team11.UTATrivia.repository.FirebaseDatabaseHelper;
import com.team11.UTATrivia.utils.Utils;


public class SignupActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText fullNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();
    }


    private void initView() {
        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);
        confirmPasswordEditText = findViewById(R.id.et_confirm_password);
        fullNameEditText = findViewById(R.id.et_full_name);
    }


    public void onLoginTextViewClicked(View view) {
        finish();
        startActivity(new Intent(SignupActivity.this, LoginActivity.class)); // if user clicks the login text
    }


    public void onRegisterButtonClicked(View view) {
        //fetch the text from the fields
        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        String fullName = fullNameEditText.getText().toString().trim();


        //validate data if the data entered is correct
        if ((!TextUtils.isEmpty(email)) && (!TextUtils.isEmpty(password)) && (!TextUtils.isEmpty(confirmPassword))
                && !TextUtils.isEmpty(fullName)) {
            if (Utils.validateEmail(email)) {
                if (Utils.validatePassword(password)) {
                    if (Utils.validatePassword(confirmPassword)) {
                        if (password.equals(confirmPassword)) {
                            if (FirebaseDatabaseHelper.getUserByEmail(email) == null) // check if the user already exist or not
                                createLogin(fullName, email, password);
                            else
                                Utils.error(getApplicationContext(), "User already exist");
                        } else {
                            passwordEditText.setError("Passwords doesn't match");
                            confirmPasswordEditText.setError("Passwords doesn't match");
                            Utils.error(getApplicationContext(), "Passwords doesn't match");
                            passwordEditText.setText(null);
                            confirmPasswordEditText.setText(null);
                        }
                    } else {
                        confirmPasswordEditText.setError("Confirm Password not valid");
                        confirmPasswordEditText.setText(null);
                    }

                } else {
                    passwordEditText.setError("Password is not valid");
                    passwordEditText.setText(null);
                    confirmPasswordEditText.setText(null);
                }

            } else {
                Utils.error(getApplicationContext(), "Email is not valid");
                emailEditText.setError("Email is not valid");
                emailEditText.setText(null);
            }
        } else {
            Utils.error(getApplicationContext(), "Please fill all the fields to continue");
            emailEditText.setError("Required");
            passwordEditText.setError("Required");
            confirmPasswordEditText.setError("Required");
            fullNameEditText.setError("Required");
        }

    }


    private void createLogin(final String fullName, final String email, final String password) {

        ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.show();

        User user = new User(fullName, email, password, "", "Online", 0, 0);
        FirebaseDatabaseHelper.createUser(user, null);
        FirebaseDatabaseHelper.getAllUsers().add(user);

        progressDialog.dismiss();
        emailEditText.setText(null);
        passwordEditText.setText(null);
        confirmPasswordEditText.setText(null);
        fullNameEditText.setText(null);

        Utils.success(getApplicationContext(), "Account created successfully");
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
