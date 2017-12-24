package com.example.furqanshahid.quizzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentHomePageView extends AppCompatActivity {

    //class variables that will be used

    private static Button selectQuizBtn;

    //the onCreate method is called whenever the activity starts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page_view);
        selectQuiz();
    }

    //this method switches the activity for the student

    public void selectQuiz(){

        //here we link the class variables with the respective design elements

        selectQuizBtn =(Button)findViewById(R.id.selectQuizBtn);

        //the click listner is implemented here that performs some action when the createNewQuizbtn is clicked
        // i.e. switches the activity

        selectQuizBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent("com.example.furqanshahid.quizzer.AttemptQuiz");
                        startActivity(intent);
                    }
                }
        );
    }

}
