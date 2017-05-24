package group6.tcss450.uw.edu.hackalong.tasks;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import group6.tcss450.uw.edu.hackalong.EditProfileFragment;

/**
 * Created by Devin on 5/23/2017.
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


    @Override
    protected void onPostExecute(String result) {
        if (result.startsWith("Unable to")) {
            // Something wrong with the network or the URL.
            mListener.onEditTaskError(result);
        } else {
            mListener.onEditTaskCompletion(result);
        }

    }


    public interface OnEditTaskCompleteListener {
        void onEditTaskCompletion(String message);
        void onEditTaskError(String error);
    }
}


