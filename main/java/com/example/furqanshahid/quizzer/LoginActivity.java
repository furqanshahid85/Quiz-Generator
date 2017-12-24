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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    //class variables that will be used

    private static EditText userEmail,userPassword;
    private static Button loginBtn;
    private static CheckBox userRoleStudent;
    private static CheckBox userRoleTeacher;

    @Override

    //the onCreate method is called whenever the activity starts
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        userLogin();
    }

    //here userLogin method is implemented that logs the userIn
    public void userLogin(){
        //here we link the class variables with the respective design elements
        userEmail=(EditText)findViewById(R.id.userEmailTextView);
        userPassword=(EditText)findViewById(R.id.userPasswordTextView);
        loginBtn=(Button)findViewById(R.id.loginButton);
        userRoleStudent=(CheckBox)findViewById(R.id.userRoleStudentCBox);
        userRoleTeacher=(CheckBox)findViewById(R.id.userRoleTeacherCBox);

        //the click listner is performs some action when the loginbtn is clicked
        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userRole;
                        if (userRoleTeacher.isChecked()){
                            userRole="teacher";
                        }else if(userRoleStudent.isChecked()){
                            userRole="student";
                        }else{
                            userRole=null;
                        }

                        //here we check if internet is connected or not and make a get request to the given url
                        if (isOnline())
                            requestData("http://192.168.137.1:50012/userLoggedIn?userEmail="+
                                    userEmail.getText().toString()+"&userPassword="+userPassword.getText().toString()+"&userRole="+userRole);
                        else
                            Toast.makeText(LoginActivity.this,"network not available",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    //this method gets the url when the loginbtn is clicked,
    // creates a new instance of the mytask class and passes it to the doInBackground function
    private void requestData(String uri) {
        myTask task=new myTask();
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

    private class myTask extends AsyncTask<String, String ,String>{

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

            String content= "empty";
            try {
                HttpManager httpManager = new HttpManager();
                content=httpManager.getData(String.valueOf(strings[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content;
        }

        //invoked on the UI thread after the background computation finishes.
        // The result of the background computation is passed to this step as a parameter.

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            //here we authenticate the user and switch the activity
            if(s.trim().equals("T")){
                Toast.makeText(LoginActivity.this,"login success,teacher"+s,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent("com.example.furqanshahid.quizzer.TeacherHomePageView");
                startActivity(intent);
            }else if(s.trim().equals("S")){

                Toast.makeText(LoginActivity.this,"login success ,student"+s,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent("com.example.furqanshahid.quizzer.StudentHomePageView");
                startActivity(intent);

            }else{
                Toast.makeText(LoginActivity.this,"login failed : "+s , Toast.LENGTH_SHORT).show();
            }
        }
    }

}
