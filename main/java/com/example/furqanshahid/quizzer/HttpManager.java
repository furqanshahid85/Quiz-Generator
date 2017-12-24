package com.example.furqanshahid.quizzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by furqan shahid on 24-Dec-17.
 */

//this class handles the http requests,makes connections
// and returns the results obtained from a request
public class HttpManager {


//the url from different methods is passed to this func. it creates a url connection and
    public static String getData(String urlString) throws IOException{
        //Http connection is created
        HttpURLConnection urlConnection = null;

        //url from methods is passed and url obj is created
        URL url = new URL(urlString);

        //we link the url to our connection
        urlConnection = (HttpURLConnection) url.openConnection();

        //here we define the type of method that will be implemented.i.e get,post,delete,put
        urlConnection.setRequestMethod("GET");

        //connection timeout is the timeout in making the initial connection; i.e. completing the TCP connection handshake.
        // The read timeout is the timeout on waiting to read data
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );

        //
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        //this is used to store the incoming data from the requested url
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        return jsonString;
    }

}
