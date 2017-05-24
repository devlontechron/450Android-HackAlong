/**
 * TCSS 450 Spring 2017 Group 6
 * EventsFragment.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import group6.tcss450.uw.edu.hackalong.tasks.EventsWebService;


/**
 * This class holds the different events information sent to it by the webservice
 */
public class EventsFragment extends MainPageFragment implements EventsWebService.OnEventsTaskCompleteListener {

    private TextView mTextView;
    private MainPageFragment.OnFragmentInteractionListener mListener;
    String JSONRecieved;



    /**
     * Required empty constructor
     */
    public EventsFragment() {
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
        View v = inflater.inflate(R.layout.fragment_events, container, false);
        mTextView = (TextView) v.findViewById(R.id.EventsText);
        onClick(v);
        return v;

    }

    /**
     * handles events in the event of clicking the update button to get Event information
     * @param view
     */
    @Override
    public void onClick(View view){
        EventsWebService task = new EventsWebService(EventsFragment.this);
        task.execute();
        //updateContent(JSONRecieved);
    }

    /**
     * helper method that updates textView after Async task of recieving info
     * @param JSONRecieved
     */
    private void updateContent(String JSONRecieved) {
        TextView display = (TextView) getActivity().findViewById(R.id.EventsText);
     //   display.setText(JSONRecieved);
    }

    /**
     * events for once the fragment is attached to an activity
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
     * called once the fragment is about to leave the activity
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * parses the recieved JSON from async task EventsWebService
     * @param json
     */
    private void parseJSON(final String json) {
        mTextView.setText(json);
    }

    /**
     * return from a successful Async task of EventsWebService
     * @param message
     */
    @Override
    public void onEventsTaskCompletion(String message) {
        parseJSON(message);
    }
    /**
     * return from a failed Async task of EventsWebService
     * toast notification of error
     * @param error
     */
    @Override
    public void onEventsTaskError(String error) {
        Toast.makeText( getActivity().getApplicationContext(), "An error occured while getting the EventsFragment. Try again Later",Toast.LENGTH_LONG).show();
    }


}
