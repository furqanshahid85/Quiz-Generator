package com.example.furqanshahid.quizzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TeacherHomePageView extends AppCompatActivity {
    //class variables that will be used

    private static Button createNewQuizBtn;

    //the onCreate method is called whenever the activity starts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_page_view);
        createQuiz();
    }

    //this method switches the activity for the teacher

    public void createQuiz(){
        //here we link the class variables with the respective design elements

        createNewQuizBtn=(Button)findViewById(R.id.createQuizBtn);

        //the click listner is implemented here that performs some action when the createNewQuizbtn is clicked
        // i.e. switches the activity

        createNewQuizBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent("com.example.furqanshahid.quizzer.AddQuizCredentials");
                        startActivity(intent);
                    }
                }
        );
    }


}
