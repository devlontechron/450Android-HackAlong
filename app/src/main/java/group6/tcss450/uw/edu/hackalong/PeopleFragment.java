/**
 * TCSS 450 Spring 2017 Group 6
 * PeopleFragment.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * This class is unimplemented, but will display a list of people.
 */
public class PeopleFragment extends MainPageFragment {
    /* the fragment listener */
    private OnFragmentInteractionListener mListener;

    /**
     * Required empty constructor
     */
    public PeopleFragment() {
    }

    /**
     * Creates the view for the fragment.
     * @param inflater inflates the fragment
     * @param container the container for the fragment
     * @param savedInstanceState the previous state, if any
     * @return returns the inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_people, container, false);
    }

    /**
     * Checks to make sure the context is an OnFragmentInteractionListener
     * @param context the context for this class
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
