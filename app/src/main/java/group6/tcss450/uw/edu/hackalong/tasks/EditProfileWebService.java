/**
 * TCSS 450 Spring 2017 Group 6
 * EditProfileWebSErvice.java
 * May 28, 2017
 */


package group6.tcss450.uw.edu.hackalong.tasks;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Async task to save and UPDATE user information in database
 * called from editUserProfile
 */
public class EditProfileWebService extends AsyncTask<Void, Void, String>{

    private OnEditTaskCompleteListener mListener;
    private String userE;
    private String userN = "";
    private String userL = "Earth";
    private int userA = 18;
    private String userT = "";
    private String userB = "";
    private String userEv = "";

    /**
     * recieves user input and stores to variable
     * @param listner
     * @param userEmail
     * @param Uname
     * @param ULoc
     * @param UAge
     * @param UEv
     * @param UB
     * @param UT
     */
    public EditProfileWebService(final OnEditTaskCompleteListener listner, String userEmail, String Uname, String ULoc, int UAge, String UEv, String UB, String UT) {
        super();
        mListener = listner;
        userE = userEmail;
        userN = Uname;
        userL = ULoc;
        userA = UAge;
        userT = UT;
        userB = UB;
        userEv = UEv;

    }

    /**
     * connects to web service and puts recieved user input into database
     * @param params
     * @return
     */
    @Override 
    protected String doInBackground(Void... params) {
        String response = "";
        HttpURLConnection urlConnection = null;
        try {
            URL urlObject = new URL("http://cssgate.insttech.washington.edu/~d1durham/updateUser.php?UE=" +userE +"&UPW=Tests"+ "&UN=" + userN+ "&UL=" + userL+ "&UA=" + userA+ "&UEv="+userEv + "&UB=" + userB + "&UT=" + userT);
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
     * checks the response from the web service after task has completed
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {
        if (result.startsWith("Unable to")) {
            // Something wrong with the network or the URL.
            mListener.onEditTaskError(result);
        } else {
            mListener.onEditTaskCompletion(result);
        }

    }

    /**
     * Listener for task completion
     */
    public interface OnEditTaskCompleteListener {
        void onEditTaskCompletion(String message);
        void onEditTaskError(String error);
    }
}


