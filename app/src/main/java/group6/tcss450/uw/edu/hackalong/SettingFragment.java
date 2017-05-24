/*
 * TCSS 450 Spring 2017 Group 6
 * SettingFragment.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



/**
 * This class controls the Registration fragment
 */
public class SettingFragment extends Fragment implements View.OnClickListener {
    /* The fragment listener */
    private MainPageFragment.OnFragmentInteractionListener mListener;

    /**
     * Required empty constructor
     */

    String password;
    String username;

    public SettingFragment() {
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
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        Button b = (Button) v.findViewById(R.id.logoutButton);
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
        if (context instanceof MainPageFragment.OnFragmentInteractionListener) {
            mListener = (MainPageFragment.OnFragmentInteractionListener) context;
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

          switch (v.getId()) {
              case R.id.logoutButton:
                  MainPageFragment mainPageFragment = new MainPageFragment();
                  getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mainPageFragment).addToBackStack(null).commit();
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
