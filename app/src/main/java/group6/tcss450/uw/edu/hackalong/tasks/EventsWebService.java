package group6.tcss450.uw.edu.hackalong.tasks;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class EventsWebService extends AsyncTask<Void, Void, String> {


    private OnEventsTaskCompleteListener mListener;

    public EventsWebService(final OnEventsTaskCompleteListener listner) {
        super();
        mListener = listner;
    }


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

    @Override
    protected void onPostExecute(String result) {
        if (result.startsWith("Unable to")) {
            // Something wrong with the network or the URL.
            mListener.onEventsTaskError(result);
        } else {
            mListener.onEventsTaskCompletion(result);
        }

    }

    public interface OnEventsTaskCompleteListener {
        void onEventsTaskCompletion(String message);
        void onEventsTaskError(String error);
    }
}
