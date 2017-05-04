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


/**
 * This class controls the Registration fragment
 */
public class Register extends MainPage {
    /* The fragment listener */
    private OnFragmentInteractionListener mListener;

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
        String username = "";
        String password = "";
        String reenterPassword = "";
//        if (mListener != null && password.equals(reenterPassword)) {
            switch (v.getId()) {
                case R.id.registrationbutton:
                    mListener.onFragmentInteraction("events", username, password);
                    break;
            }
//        }
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
