package com.example.furqanshahid.quizzer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class AddQuizCredentials extends AppCompatActivity {

    //class variables that will be used

    private static EditText quizTitle,quizDescription,quizMarks,quizAttemptTime;
    private static Button saveBtn;
    private static int quizId;

    //the onCreate method is called whenever the activity starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz_credentials);
        saveCredentials();
    }

    //this method saves the necessary credentials required for the quiz

    public void saveCredentials(){

        //here we link the class variables with the respective design elements

        quizTitle=(EditText)findViewById(R.id.quizTitleTextView);
        quizDescription=(EditText)findViewById(R.id.quizDescriptonTextView);
        quizMarks=(EditText)findViewById(R.id.quizMarksTextView);
        quizAttemptTime=(EditText)findViewById(R.id.quizATimeTextView);
        saveBtn=(Button)findViewById(R.id.saveBtn);

        //the click listner is implemented here that performs some action when the savebtn is clicked
        // i.e. save the credentials

        saveBtn.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //here we check if internet is connected or not and make a get request to the given url

                        if (isOnline()){
                            requestData("http://192.168.137.1:50012/createNewQuiz?quizTitle=" + quizTitle.getText().toString() +
                                    "&quizDes=" + quizDescription.getText().toString() + "&quizMarks=" + quizMarks.getText().toString() +
                                    "&quizTime=" + quizAttemptTime.getText().toString());

                            Intent intent = new Intent("com.example.furqanshahid.quizzer.AddQuizQuestions");
                            startActivity(intent.putExtra("quizID",quizId));
                        }else
                            Toast.makeText(AddQuizCredentials.this,"network not available",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    //this method gets the url when the savebtn is clicked,
    // creates a new instance of the mytask class and passes it to the doInBackground function

    private void requestData(String uri) {
        AddQuizCredentials.myTask task=new AddQuizCredentials.myTask();
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
            String ss=s;
            Toast.makeText(AddQuizCredentials.this,"quiz created"+s,Toast.LENGTH_LONG).show();
            try {
                quizId= Integer.parseInt(ss);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
