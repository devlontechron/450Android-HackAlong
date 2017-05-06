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

    public LoginWebService(final LoginWebService.OnLoginTaskCompleteListener listner, String username) {
        super();
        mListener = listner;
        UE = username;
    }

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

    @Override
    protected void onPostExecute(String result) {
        if (result.startsWith("Unable to")) {
            // Something wrong with the network or the URL.
            mListener.onLoginTaskError(result);
        } else {
            mListener.onLoginTaskCompletion(result);
        }

    }

    public interface OnLoginTaskCompleteListener {
        void onLoginTaskCompletion(String message);

        void onLoginTaskError(String error);
    }
}
