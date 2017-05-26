/**
 * TCSS 450 Spring 2017 Group 6
 * PeopleFragment.java
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

import java.util.ArrayList;

import group6.tcss450.uw.edu.hackalong.tasks.PeopleWebService;


/**
 * This class is unimplemented, but will display a list of people.
 */
public class PeopleFragment extends LoginFragment implements PeopleWebService.OnPeopleTaskCompleteListener {
    /* the fragment listener */
    private OnFragmentInteractionListener mListener;
    private TextView mTextView;
    protected RecyclerView mRecyclerView;
    ArrayList<String> mDataset = new ArrayList<String>();
    ArrayList<String> peopleLocData = new ArrayList<String>();
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
        View v = inflater.inflate(R.layout.recyclerviewpeople, container, false);
        mTextView = (TextView) v.findViewById(R.id.people3);
        FloatingActionButton F = (FloatingActionButton) v.findViewById(R.id.FABPeopleSearch);
        //F.setOnClickListener(this);
        loadPeopleData(v);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.peoplelist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadPeopleData(v);
        //Adapter mAdapter = new Adapter(eventName,eventLoc,eventDate);
       // mRecyclerView.setAdapter(mAdapter);
        return v;

    }


    @Override
    public void onClick(View view){
        if (mListener != null) {
            switch (view.getId()) {
                case R.id.FABPeopleSearch:
                    mListener.onFragmentInteraction("eventSearch", null, null);
                    break;

                default:
                    break;

            }
        }
    }

    public void loadPeopleData(View v){
        PeopleWebService task = new PeopleWebService(PeopleFragment.this);
        task.execute();
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



    private void parseJSON(final String json) {
        JSONArray jObject;
        ArrayList<String> myPeopleList = new ArrayList<String>();


        try {
            jObject = new JSONArray(json);
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject event = jObject.getJSONObject(i);

                mDataset.add(event.getString("UName"));
                peopleLocData.add(event.getString("ULocation"));

            }

        }catch (JSONException e){
            Log.e("JSON","events");
        }

        AdapterPeople mAdapter = new AdapterPeople(mDataset,peopleLocData);
        mRecyclerView.setAdapter(mAdapter);
    }


    /**
     * Calls the super method and sets the mListener to null
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPeopleTaskCompletion(String message) {
        parseJSON(message);

    }

    @Override
    public void onPeopleTaskError(String error) {
        Toast.makeText( getActivity().getApplicationContext(), "An error occured while getting the EventsFragment. Try again Later",Toast.LENGTH_LONG).show();
    }
}
