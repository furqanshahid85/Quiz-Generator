package com.example.furqanshahid.quizzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class AttemptQuiz extends AppCompatActivity {
    //class variables that will be used

    private static Button quizAttemptBtn;
    ListView quizList;

    //the onCreate method is called whenever the activity starts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //here we link the class variables with the respective design elements

        setContentView(R.layout.activity_attempt_quiz);

        String[] arr = {"quiz1","quiz2","quiz3","quiz4","quiz5"};

        //here we pass our array of elements to ArrayAdapter
        final ArrayAdapter adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1,arr);

        //a quiz listview is created that will hold the saved quizes that student can attempt
        quizList =(ListView)findViewById(R.id.listquiz);
        quizList.setAdapter(adapter);

        //a pop will be created when the user clicks on any item of the listview
        quizList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AttemptQuiz.this , String.valueOf(adapter.getItem(i)), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
