/**
 * TCSS 450 Spring 2017 Group 6
 * SettingFragment.java
 * May 19, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
  

/**
 * This class controls the user profile
 */
public class SettingFragment extends LoginFragment implements View.OnClickListener {
    /* the fragment listener */
    private LoginFragment.OnFragmentInteractionListener mListener;

    /**
     * Required empty constructor
     */
    public SettingFragment() {
    }

    /**
     * Creates the view for the fragment
     *
     * @param inflater           inflates the fragment
     * @param container          the container that holds the fragment
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



    public void onClick(View v){
        if(mListener!=null){
        //dialog

        SharedPreferences mPref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        mPref.edit().clear();
        mPref.edit().putBoolean(getString(R.string.isLoggedIn), false).apply();
        if (mListener != null) {
            mListener.onFragmentInteraction("login", null, null);
        }
        }
    }

    /**
     * Checks the context to make sure it is an OnFragmentInteractionListener
     *
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
     * Calls the super method and sets the mListener to null
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
