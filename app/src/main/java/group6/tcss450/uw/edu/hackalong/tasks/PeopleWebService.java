/**
 * TCSS 450 Spring 2017 Group 6
 * PeopleWebService.java
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
 * AsyncTask to GET all people profiles from database to be used in peopleFragment
 */
public class PeopleWebService extends AsyncTask<Void, Void, String> {

    private OnPeopleTaskCompleteListener mListener;

    public PeopleWebService(final OnPeopleTaskCompleteListener listner) {
        super();
        mListener = listner;
    }


    /**
     * Task to be done in backgrond thread
     * connects to service
     * reads response and returns
     * @param none
     * @return response
     */
    @Override
    protected String doInBackground(Void... none) {
        String response = "";
        HttpURLConnection urlConnection = null;
        try {
            URL urlObject = new URL("http://cssgate.insttech.washington.edu/~d1durham/getAllPeople.php");
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
     * task to be carried out after ocmpletion of doInBackground
     * sends a completion or error back to PeopleFragment
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {
        if (result.startsWith("Unable to")) {
            // Something wrong with the network or the URL.
            mListener.onPeopleTaskError(result);
        } else {
            mListener.onPeopleTaskCompletion(result);
        }

    }


    /**
     * calls methods for compleation of task
     */
    public interface OnPeopleTaskCompleteListener {
        void onPeopleTaskCompletion(String message);
        void onPeopleTaskError(String error);
    }
}