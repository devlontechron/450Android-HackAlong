/**
 * TCSS 450 Spring 2017 Group 6
 * Events.java
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
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import group6.tcss450.uw.edu.hackalong.tasks.EventsWebService;


/**
 * This class holds the different events information sent to it by the webservice
 */
public class Events extends MainPage implements EventsWebService.OnEventsTaskCompleteListener {

    private TextView mTextView;
    private MainPage.OnFragmentInteractionListener mListener;
    String JSONRecieved;



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
        View v = inflater.inflate(R.layout.fragment_events, container, false);
        mTextView = (TextView) v.findViewById(R.id.EventsText);
        Button getEventsButton = (Button) v.findViewById(R.id.EventsButton);
        getEventsButton.setOnClickListener(this);
        return v;

    }

    @Override
    public void onClick(View view){
        EventsWebService task = new EventsWebService(Events.this);
        task.execute();


        updateContent(JSONRecieved);

    }

    private void updateContent(String JSONRecieved) {
        TextView display = (TextView) getActivity().findViewById(R.id.EventsText);
        display.setText(JSONRecieved);
    }


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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private void parseJSON(final String json) {
        mTextView.setText(json);
        /*
        try {
            JSONObject object = new JSONObject(json);
            if (object.getInt("error_code") == 0) {

                mTextView.setText(object.get);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void onEventsTaskCompletion(String message) {
        parseJSON(message);
    }

    @Override
    public void onEventsTaskError(String error) {

    }


}
