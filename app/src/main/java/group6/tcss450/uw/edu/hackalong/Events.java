/**
 * TCSS 450 Spring 2017 Group 6
 * Events.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * This class holds the different events information sent to it by the webservice
 */
public class Events extends Fragment {

    /**
     * Required empty constructor
     */
    public Events() {
    }

    /**
     * This creates the view for the fragment
     * @param inflater inflates the view
     * @param container contains the view
     * @param savedInstanceState contains the previous state, if there was one
     * @return returns inflated fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);

    }

}
