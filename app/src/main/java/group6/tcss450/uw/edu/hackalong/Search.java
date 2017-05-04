/**
 * TCSS 450 Spring 2017 Group 6
 * Search.java
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


/**
 * Unimplemented class, will allow searching for people or events
 */
public class Search extends MainPage {
    /* The fragment listener */
    private OnFragmentInteractionListener mListener;

    /**
     * Required empty constructor
     */
    public Search() {
    }

    /**
     * Creates the view for this fragment
     * @param inflater inflates the fragment
     * @param container the container that holds the fragment
     * @param savedInstanceState the previous state, if any
     * @return returns the inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    /**
     * Checks to see if the context is an OnFragmentInteractionListener
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
     * Calls the super method and sets mListener to null
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
