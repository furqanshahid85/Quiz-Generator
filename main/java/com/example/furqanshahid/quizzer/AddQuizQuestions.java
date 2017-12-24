package com.example.furqanshahid.quizzer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class AddQuizQuestions extends AppCompatActivity {

    //class variables that will be used

    private static EditText quizQuestion,optA,optB,optC,optD,correctAns;
    private static Button saveQuizBtn;
    private static CheckBox mcq,blank,trueFalse;
    private static int quizId;
    private static String qType;


    //the onCreate method is called whenever the activity starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz_questions);
        Intent intent=getIntent();
        intent.getIntExtra("quizID",-1);
        saveQuizQuestion();
    }

    //this method saves the quiz questions required for the quiz

    public void saveQuizQuestion(){

        //here we link the class variables with the respective design elements

        quizQuestion=(EditText)findViewById(R.id.questionTextView);
        optA=(EditText)findViewById(R.id.optATextView);
        optB=(EditText)findViewById(R.id.optBTextView);
        optC=(EditText)findViewById(R.id.optCTextView);
        optD=(EditText)findViewById(R.id.optDTextView);
        correctAns=(EditText)findViewById(R.id.correctAnsTextView);
        saveQuizBtn=(Button)findViewById(R.id.saveQuizBtn);
        mcq=(CheckBox)findViewById(R.id.mcqCBox);
        blank=(CheckBox)findViewById(R.id.blankCBox);
        trueFalse=(CheckBox)findViewById(R.id.trueFalseCBox);

        //the click listner is implemented here that performs some action when the savebtn is clicked
        // i.e. saves the questions

        saveQuizBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (mcq.isChecked()){
                            qType="mcq";
                        }else if (blank.isChecked()) {
                            qType="blank";
                        }else{
                            qType="trueFalse";
                        }
//here we check if internet is connected or not and make a get request to the given url

                        if (isOnline()){
                            requestData("http://192.168.137.1:50012/addQuizQuestions?quizId="+quizId+"&quizQuestion="
                                    + quizQuestion.getText().toString()+"&questionType="+ qType+
                                    "&optA=" + optA.getText().toString() + "&optB=" + optB.getText().toString() +
                                    "&optC=" + optC.getText().toString()+"&optD=" + optD.getText().toString()+
                                    "&correctAns=" + correctAns.getText().toString());
                        }else
                            Toast.makeText(AddQuizQuestions.this,"network not available",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    //this method gets the url when the savebtn is clicked,
    // creates a new instance of the mytask class and passes it to the doInBackground function

    private void requestData(String uri) {
        AddQuizQuestions.myTask task=new AddQuizQuestions.myTask();
        task.execute(uri);
    }/**/

    //here we check if internet connectivity is available

    protected boolean isOnline(){
        ConnectivityManager cm= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

    //AsyscTask class is used for creating and handling threads.
    // it allows us to perform background operations and
    // pass results on the UI thread without disturbing threads and/or handlers.


    private class myTask extends AsyncTask<String, String ,String> {

        //this method is called on the UI thread before any task is executed. This is used to setup the task

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

//this is the main thread that is called immediately after preExecute and used to perform background computations
        // that can take a long time.The parameters of the asynchronous task are passed to this step.
        // The result of the computation are returned by this step and will be passed back to the last step


        @Override
        protected String doInBackground(String... strings) {

            String content = "empty";
            try {
                HttpManager httpManager = new HttpManager();
                content = httpManager.getData(String.valueOf(strings[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content;
        }

        //invoked on the UI thread after the background computation finishes.
        // The result of the background computation is passed to this step as a parameter.

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(AddQuizQuestions.this,"question added",Toast.LENGTH_LONG).show();
        }
    }

}
