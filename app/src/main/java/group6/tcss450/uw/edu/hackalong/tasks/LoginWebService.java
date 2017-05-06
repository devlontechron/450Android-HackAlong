/**
 * TCSS 450 Spring 2017 Group 6
 * LoginWebServices.java
 * May 5, 2017
 */

package group6.tcss450.uw.edu.hackalong.tasks;


import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import group6.tcss450.uw.edu.hackalong.DashBoard;
import group6.tcss450.uw.edu.hackalong.MainPage;
import group6.tcss450.uw.edu.hackalong.R;

public class LoginWebService  extends AsyncTask<Void, Void, String> {

    private LoginWebService.OnLoginTaskCompleteListener mListener;
    String UE;

    /**
     * simple constructer to accapt information to later be used
     * @param listner
     * @param username
     */
    public LoginWebService(final LoginWebService.OnLoginTaskCompleteListener listner, String username) {
        super();
        mListener = listner;
        UE = username;
    }

    /**
     * reaching out to external database for login user to get user information bassed off of email
     * @param none
     * @return
     */
    @Override
    protected String doInBackground(Void... none) {
        String response = "";
        HttpURLConnection urlConnection = null;
        try {
            Log.e("HELP", UE);
            URL urlObject = new URL("http://cssgate.insttech.washington.edu/~d1durham/getUser.php?UE="+UE);
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
            mListener.onLoginTaskError(result);
        } else {
            mListener.onLoginTaskCompletion(result);
        }

    }
    /**
     * methods for other methods
     */
    public interface OnLoginTaskCompleteListener {
        void onLoginTaskCompletion(String message);

        void onLoginTaskError(String error);
    }
}
