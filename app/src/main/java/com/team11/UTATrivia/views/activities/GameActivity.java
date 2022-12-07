package com.team11.UTATrivia.views.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.team11.UTATrivia.R;
import com.team11.UTATrivia.models.Quiz;
import com.team11.UTATrivia.models.User;
import com.team11.UTATrivia.repository.CurrentDatabase;
import com.team11.UTATrivia.repository.FirebaseDatabaseHelper;
import com.team11.UTATrivia.utils.Constants;
import com.team11.UTATrivia.utils.QuizBank;
import com.team11.UTATrivia.utils.Stopwatch;
import com.team11.UTATrivia.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private int counter = 0;
    private int score = 0;
    private int category = 0;

    private ImageView exitImageView;

    private TextView currentScoreTextView;

    private Button nextButton;
    private TextView progressTextView;
    private TextView questionTextView;
    private TextView timerTextView;

    private RadioGroup questionRadioGroup;

    private RadioButton option1RadioButton;
    private RadioButton option2RadioButton;
    private RadioButton option3RadioButton;
    private RadioButton option4RadioButton;

    private Quiz currentQuestion;

    private Stopwatch stopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        category = getIntent().getIntExtra("CATEGORY", 1);

        exitImageView = findViewById(R.id.iv_exit);
        exitImageView.setOnClickListener(this);

        currentScoreTextView = findViewById(R.id.tv_current_score);

        nextButton = findViewById(R.id.btn_next);
        nextButton.setOnClickListener(this);

        progressTextView = findViewById(R.id.tv_progress);
        questionTextView = findViewById(R.id.tv_question);
        timerTextView = findViewById(R.id.tv_timer);

        questionRadioGroup = findViewById(R.id.rg_question);

        option1RadioButton = findViewById(R.id.rb_option_1);
        option2RadioButton = findViewById(R.id.rb_option_2);
        option3RadioButton = findViewById(R.id.rb_option_3);
        option4RadioButton = findViewById(R.id.rb_option_4);

        updateQuestion();

        try {
            setTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTimer() {
        stopwatch = new Stopwatch();
        stopwatch.setCurrentTime(System.currentTimeMillis());
        stopwatch.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    timerTextView.setText(stopwatch.getStringTime());
                });
            }
        }, 1000, 1000); // updating time after every 1 second (1000 means 1 second)
    }

    private void updateQuestion() {
        if (counter == Constants.TOTAL_NUMBER_OF_QUESTIONS) {
            nextButton.setEnabled(false);
            User user = CurrentDatabase.getUser();
            user.setPoints(user.getPoints() + score);
            stopwatch.stop();

            if (user.getPoints() >= 100) {
                user.setLevel(user.getLevel() + 1);
                user.setPoints(0);
            }

            FirebaseDatabaseHelper.updateUser(user);
            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
            builder.setTitle("Result");
            builder.setMessage("Congratulations!! Your score is: " + score + "\n Time taken: " + timerTextView.getText());
            builder.setPositiveButton("Close", (dialog, which) -> {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            });
            builder.setNegativeButton("Play Again", ((dialog, which) -> {
                finish();
                startActivity(new Intent(getApplicationContext(), GameActivity.class));
            }));
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {

            // selecting the category
            switch (category) {
                case 1:
                    currentQuestion = QuizBank.getCategory1QuizList().get(counter);
                    break;
                case 2:
                    currentQuestion = QuizBank.getCategory2QuizList().get(counter);
                    break;
                case 3:
                    currentQuestion = QuizBank.getCategory3QuizList().get(counter);
                    break;
            }
            counter++;

            questionTextView.setText(currentQuestion.getQuestion());
            option1RadioButton.setText(currentQuestion.getOption1());
            option2RadioButton.setText(currentQuestion.getOption2());
            option3RadioButton.setText(currentQuestion.getOption3());
            option4RadioButton.setText(currentQuestion.getOption4());

            questionRadioGroup.clearCheck();
            progressTextView.setText("" + counter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_exit: // if the quiz is terminated
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
            case R.id.btn_next: // if next button is pressed
                nextButtonPressed();
                break;
        }
    }

    // deciding if the answer in option selected is correct or not
    private void nextButtonPressed() {
        switch (questionRadioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_option_1:
                if (currentQuestion.getCorrectOption() == 1) {
                    score++;
                    currentScoreTextView.setText("" + score);
                } else
                    Utils.warning(getApplicationContext(), "Incorrect option");
                updateQuestion();
                break;
            case R.id.rb_option_2:
                if (currentQuestion.getCorrectOption() == 2) {
                    score++;
                    currentScoreTextView.setText("" + score);
                } else
                    Utils.warning(getApplicationContext(), "Incorrect option");
                updateQuestion();
                break;
            case R.id.rb_option_3:
                if (currentQuestion.getCorrectOption() == 3) {
                    score++;
                    currentScoreTextView.setText("" + score);
                } else
                    Utils.warning(getApplicationContext(), "Incorrect option");
                updateQuestion();
                break;
            case R.id.rb_option_4:
                if (currentQuestion.getCorrectOption() == 4) {
                    score++;
                    currentScoreTextView.setText("" + score);
                } else
                    Utils.warning(getApplicationContext(), "Incorrect option");
                updateQuestion();
                break;
            default:
                Utils.error(getApplicationContext(), "Please select an option");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
