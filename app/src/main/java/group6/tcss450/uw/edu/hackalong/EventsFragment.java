/**
 * TCSS 450 Spring 2017 Group 6
 * EventsFragment.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import group6.tcss450.uw.edu.hackalong.tasks.EventsWebService;


/**
 * This class holds the different events information sent to it by the webservice
 */
public class EventsFragment extends LoginFragment implements EventsWebService.OnEventsTaskCompleteListener {

    private TextView mTextView;
    private LoginFragment.OnFragmentInteractionListener mListener;
    ArrayList<String> mDataset = new ArrayList<String>();
    ArrayList<String> eventLocData = new ArrayList<String>();
    ArrayList<String> eventDateData = new ArrayList<String>();
    ArrayList<String> eventID = new ArrayList<String>();



    protected RecyclerView mRecyclerView;

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
        View v = inflater.inflate(R.layout.recycler_view_events, container, false);
        loadEvents(v);
        mTextView = (TextView) v.findViewById(R.id.EventsText);
     //  FloatingActionButton F = (FloatingActionButton) v.findViewById(R.id.FABEventSearch);
        //F.setOnClickListener(this);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;

    }

    /**
     * handles events in the event of clicking the update button to get Event information
     * @param view
     */
    @Override
    public void onClick(View view){
        if (mListener != null) {
            switch (view.getId()) {
                case R.id.FABEventSearch:
                    mListener.onFragmentInteraction("eventSearch", null, null);
                    break;

                default:
                    break;

            }
        }
    }

    public void onClickCard(View v){

    }

    public void loadEvents(View view){
        EventsWebService task = new EventsWebService(EventsFragment.this);
        task.execute();
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
    private void parseJSON(final String json) throws JSONException, ParseException {
        mDataset.clear();
        eventDateData.clear();
        eventDateData.clear();
        eventID.clear();
        JSONArray jObject = new JSONArray();
        ArrayList<Date>  myEventList = new ArrayList<Date>();


        try {
            jObject = new JSONArray(json);
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject event = jObject.getJSONObject(i);

                String eventDate = event.getString("EDate");
                DateFormat format = new SimpleDateFormat("yyyy, MMM dd, hh:mm aa", Locale.ENGLISH);
                Date eventDateDate = format.parse(eventDate);
                Date currDay = new Date();

                if (eventDateDate.before(currDay)) {
                } else if(myEventList.size()==0) {
                    myEventList.add(eventDateDate);
                    mDataset.add(event.getString("EName"));
                    eventLocData.add(event.getString("ELocation"));
                    eventID.add(event.getString("EID"));
                    eventDateData.add(event.getString("EDate"));
                }else{
                    int hasBennAdded =0;
                    int j=  0;
                    while(hasBennAdded==0) {

                        if(j==myEventList.size() ){
                            myEventList.add(j,eventDateDate);
                            mDataset.add(j,event.getString("EName"));
                            eventLocData.add(j,event.getString("ELocation"));
                            eventID.add(j,event.getString("EID"));
                            eventDateData.add(j,event.getString("EDate"));
                            hasBennAdded=1;

                        }else if(eventDateDate.after(myEventList.get(j))){
                            j++;

                        }else{

                            myEventList.add(j,eventDateDate);
                            mDataset.add(j,event.getString("EName"));
                            eventLocData.add(j,event.getString("ELocation"));
                            eventID.add(j,event.getString("EID"));
                            eventDateData.add(j,event.getString("EDate"));
                            hasBennAdded=1;
                        }
                    }

                }
            }

        }catch (JSONException e){
            Log.e("JSON","events");
        }

        Adapter mAdapter = new Adapter(mDataset,eventLocData,eventDateData, eventID, jObject);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * return from a successful Async task of EventsWebService
     * @param message
     */
    @Override
    public void onEventsTaskCompletion(String message) {
        try {
            parseJSON(message);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
