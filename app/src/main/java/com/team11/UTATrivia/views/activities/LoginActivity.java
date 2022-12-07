package com.team11.UTATrivia.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.team11.UTATrivia.R;
import com.team11.UTATrivia.repository.CurrentDatabase;
import com.team11.UTATrivia.repository.FirebaseDatabaseHelper;
import com.team11.UTATrivia.utils.Utils;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {
        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
    }



    public void onLoginButtonClicked(View view) {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();


        if (Utils.validateEmail(email)
                && Utils.validatePassword(password)) {
            loginUser(email, password);
        } else {
            if (!Utils.validateEmail(email))
                emailEditText.setError("Invalid Email");
            else if (!Utils.validatePassword(password))
                passwordEditText.setError("Invalid password, password should be atleast 6 characters long");
        }
    }

    // same as signup when everything is validated then we login the user by requesting firebase
    private void loginUser(final String email, final String password) {
        progressBar.setVisibility(View.VISIBLE);

        Intent intent;

        if (FirebaseDatabaseHelper.getUserByEmail(email) != null) {
            intent = new Intent(LoginActivity.this, MainActivity.class);
            CurrentDatabase.setUser(FirebaseDatabaseHelper.getUserByEmail(email));

        } else {
            progressBar.setVisibility(View.GONE);
            Utils.error(getApplicationContext(), "No user found");
            return;
        }
        Intent finalIntent = intent;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        Utils.saveDataInSharedPrefs(getApplicationContext(), "email", email); // store the email in local shared prefs
                        Utils.saveDataInSharedPrefs(getApplicationContext(), "password", password);
                        CurrentDatabase.setUser(FirebaseDatabaseHelper.getUserByEmail(email));
                        Utils.success(getApplicationContext(), "Login Successful!");
                        startActivity(finalIntent);
                        finish();

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Utils.error(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage());
                    }
                });

    }


    public void onRegisterTextViewClicked(View view) {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }
}
