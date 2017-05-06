/**
 * TCSS 450 Spring 2017 Group 6
 * Register.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import group6.tcss450.uw.edu.hackalong.tasks.RegisterWebService;


/**
 * This class controls the Registration fragment
 */
public class Register extends MainPage implements RegisterWebService.OnRegisterTaskCompleteListener  {
    /* The fragment listener */
    private OnFragmentInteractionListener mListener;
    /* The email address box */
    EditText user;
    /* The password box */
    EditText pass;
    /* The password verification box */
    EditText pass2;
    /**
     * Required empty constructor
     */

    String password;
    String username;

    public Register() {
    }

    /**
     * Creates the view for this fragment and attaches the button to listener
     * @param inflater inflates the fragment
     * @param container the container that holds the fragment
     * @param savedInstanceState the previous state, if any
     * @return returns the view for this fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        Button b = (Button) v.findViewById(R.id.registrationbutton);
        b.setOnClickListener(this);
        user = (EditText) v.findViewById(R.id.registername);
        pass = (EditText) v.findViewById(R.id.registerpassword);
        pass2 = (EditText) v.findViewById(R.id.reenterpassword);

        return v;
    }

    /**
     * Checks the context to make sure it is an OnFragmentInteractionListener
     * @param context is the context of this class
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * Controls the onClick function for the registration button
     * Calls the Async task to reach databse for registering a new user
     * @param v is the view for this fragment
     */
    @Override
    public void onClick(View v) {
        if (mListener != null) {
            if (user.getText().length() > 0 && pass.getText().length() > 0) {
                password = pass.getText().toString();
                username = user.getText().toString().toLowerCase();
                String password2 = pass2.getText().toString();
                if (password.equals(password2) && password.length()>4) {
                    switch (v.getId()) {
                        case R.id.registrationbutton:
                            RegisterWebService task = new RegisterWebService(Register.this,username,password);
                            task.execute();
                            //mListener.onFragmentInteraction("events", username, password);
                            break;
                    }

                } else{
                    Toast.makeText( getActivity().getApplicationContext(), "The password is too short or dose not match.",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /**
     * Calls the super method and sets the mListener to null
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * return from the successful Async task RegisterWebService
     * @param message
     */
    @Override
    public void onRegisterTaskCompletion(String message) {
        parseRegJSON(message);

    }

    /**
     * parses the return message from external web service and confirms registration
     * after confirm, sends data and calls another frgament
     * @param message
     */
    private void parseRegJSON(String message) {
        if (message.equals("1")) {
            mListener.onFragmentInteraction("events", username, password);
            Toast.makeText( getActivity().getApplicationContext(), "Successful Registration!",Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText( getActivity().getApplicationContext(), "That email is already in use",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * return failure from Async task of RegisterWebServices
     * @param error
     */
    @Override
    public void onRegisterTaskError(String error) {
        Toast.makeText( getActivity().getApplicationContext(), "An error with the web service has occured. Try again later.",Toast.LENGTH_LONG).show();
    }
}
