/**
 * TCSS 450 Spring 2017 Group 6
 * Register.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * This class controls the Registration fragment
 */
public class Register extends MainPage {
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
     * Controls the onClick function for the registration button and sends data for calling another
     * fragment
     * @param v is the view for this fragment
     */
    @Override
    public void onClick(View v) {
        if (mListener != null) {
            if (user.getText().length() > 0 && pass.getText().length() > 0) {
                String password = pass.getText().toString();
                String username = user.getText().toString();
                String password2 = pass2.getText().toString();
                if (password.equals(password2)) {
                    switch (v.getId()) {
                        case R.id.registrationbutton:
                            mListener.onFragmentInteraction("events", username, password);
                            break;
                    }
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

}
