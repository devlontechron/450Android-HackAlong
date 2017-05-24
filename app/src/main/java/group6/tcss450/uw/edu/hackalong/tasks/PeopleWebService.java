package group6.tcss450.uw.edu.hackalong.tasks;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PeopleWebService extends AsyncTask<Void, Void, String> {

    private OnPeopleTaskCompleteListener mListener;

    public PeopleWebService(final OnPeopleTaskCompleteListener listner) {
        super();
        mListener = listner;
    }



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


    @Override
    protected void onPostExecute(String result) {
        if (result.startsWith("Unable to")) {
            // Something wrong with the network or the URL.
            mListener.onPeopleTaskError(result);
        } else {
            mListener.onPeopleTaskCompletion(result);
        }

    }



    public interface OnPeopleTaskCompleteListener {
        void onPeopleTaskCompletion(String message);
        void onPeopleTaskError(String error);
    }
}