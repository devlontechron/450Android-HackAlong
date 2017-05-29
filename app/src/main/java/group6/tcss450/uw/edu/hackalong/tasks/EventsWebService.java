/**
 * TCSS 450 Spring 2017 Group 6
 * EventsWebServices.java
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
 * Async task called from EventsFragment to reach and GET all events
 */
public class EventsWebService extends AsyncTask<Void, Void, String> {


    private OnEventsTaskCompleteListener mListener;

    /**
     * a constructor
     * @param listner
     */
    public EventsWebService(final OnEventsTaskCompleteListener listner) {
        super();
        mListener = listner;
    }

    /**
     * Async task for getting EventsFragment from database
     * calls external web service of database to retrieve all events and information
     * returns JSON response
     * @return response
     */
    @Override
    protected String doInBackground(Void... none) {
        String response = "";
        HttpURLConnection urlConnection = null;
        try {
        URL urlObject = new URL("http://cssgate.insttech.washington.edu/~d1durham/getAllEvents.php");
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
     * checks if connection was successful or responded with an error
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {
        if (result.startsWith("Unable to")) {
            // Something wrong with the network or the URL.
            mListener.onEventsTaskError(result);
        } else {
            mListener.onEventsTaskCompletion(result);
        }

    }
    /**
     * methods for other methods to call after compleation of task
     */
    public interface OnEventsTaskCompleteListener {
        void onEventsTaskCompletion(String message);
        void onEventsTaskError(String error);
    }
}
