package group6.tcss450.uw.edu.hackalong.tasks;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterWebService extends AsyncTask<Void, Void, String> {



    private OnRegisterTaskCompleteListener mListener;

    private String userE;
    private String userPW;

    public RegisterWebService(final OnRegisterTaskCompleteListener listner, String userEmail, String userPassW) {
        super();
        mListener = listner;
        userE = userEmail;
        userPW = userPassW;
    }
   
   
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


    @Override
    protected void onPostExecute(String result) {
        if (result.startsWith("Unable to")) {
            // Something wrong with the network or the URL.
            mListener.onRegisterTaskError(result);
        } else {
            mListener.onRegisterTaskCompletion(result);
        }

    }

    public interface OnRegisterTaskCompleteListener {
        void onRegisterTaskCompletion(String message);
        void onRegisterTaskError(String error);
    }
}
