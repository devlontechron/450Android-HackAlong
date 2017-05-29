/**
 * TCSS 450 Spring 2017 Group 6
 * RegisterWebServices.java
 * May 5, 2017
 */

package group6.tcss450.uw.edu.hackalong.tasks;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * AsyncTask web service to register new users and update the database
 */
public class RegisterWebService extends AsyncTask<Void, Void, String> {



    private OnRegisterTaskCompleteListener mListener;

    private String userE;
    private String userPW;

    /**
     * simple constructor for class
     * @param listner
     * @param userEmail
     * @param userPassW
     */
    public RegisterWebService(final OnRegisterTaskCompleteListener listner, String userEmail, String userPassW) {
        super();
        mListener = listner;
        userE = userEmail;
        userPW = userPassW;
    }

    /**
     * Async task for Registering a new user
     * calls external web service of database to retrieve user email and password
     * returns JSON response
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(Void... params) {
        String response = "";
        HttpURLConnection urlConnection = null;
        try {
            URL urlObject = new URL("http://cssgate.insttech.washington.edu/~d1durham/addUser.php?UE=" +userE+ "&UPW=" + userPW);
            urlConnection = (HttpURLConnection) urlObject.openConnection();

            InputStream content = urlConnection.getInputStream();

            BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
            String s = "";
            while ((s = buffer.readLine()) != null) {
                response += s;
            }

        } catch (Exception e) {
            response = "Unable to connect, Reason: "
                    + e.getMessage();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        return response;
    }

    /**
     * checks if connection was successful and calls methods
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {
        if (result.startsWith("Unable to")) {
            // Something wrong with the network or the URL.
            mListener.onRegisterTaskError(result);
        } else {
            mListener.onRegisterTaskCompletion(result);
        }

    }

    /**
     * methods for other methods
     */
    public interface OnRegisterTaskCompleteListener {
        void onRegisterTaskCompletion(String message);
        void onRegisterTaskError(String error);
    }
}
