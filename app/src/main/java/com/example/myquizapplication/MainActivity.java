package com.example.myquizapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalquestionTextview;
    TextView questionTextview;
    Button ansA, ansB, ansC, ansD;
    Button subnitBtn;


    int score = 0;

    int totalquestion = QuestionAnswer.question.length;
    int currentquestionIndex = 0;
    String selectedAnswer = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        totalquestionTextview = findViewById(R.id.total_question);
        questionTextview = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        subnitBtn = findViewById(R.id.submit_Btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        subnitBtn.setOnClickListener(this);

        totalquestionTextview.setText("Total question :" + totalquestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_Btn) {
            if (selectedAnswer.equals(QuestionAnswer.correctanswers[currentquestionIndex])) {
                score++;
            }
            currentquestionIndex++;
            loadNewQuestion();


        } else {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }

    void loadNewQuestion() {

        if (currentquestionIndex == totalquestion) {
            finishQuiz();
            return;
        }

        questionTextview.setText(QuestionAnswer.question[currentquestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentquestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentquestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentquestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentquestionIndex][3]);
    }
    void finishQuiz() {
        String passStatus = "";
        if (score > totalquestion * 0.60) {
            passStatus = "Passed";
        } else {
            passStatus = "failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("score is " + score + "out of " + totalquestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQiuz())
                .setCancelable(false)
                .show();

    }
    void restartQiuz(){
        score = 0;
        currentquestionIndex = 0;
        loadNewQuestion();

    }

}