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
 * Displays a list of people (all users) with names and locations to click and open profiles.
 */
public class PeopleFragment extends LoginFragment implements PeopleWebService.OnPeopleTaskCompleteListener {
    /* the fragment listener */
    private OnFragmentInteractionListener mListener;
    protected RecyclerView mRecyclerView;
    ArrayList<String> mDataset = new ArrayList<String>();
    ArrayList<String> peopleLocData = new ArrayList<String>();
    ArrayList<String> peopleEmail = new ArrayList<String>();


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
        View v = inflater.inflate(R.layout.recycler_view_people, container, false);

        //commented out for future implementation of search
        //FloatingActionButton F = (FloatingActionButton) v.findViewById(R.id.FABPeopleSearch);
        //F.setOnClickListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.peoplelist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Call to start Async task to get people
        loadPeopleData(v);
        return v;

    }

    /**
     * used for Search button implementation
     * @param view
     */
    @Override
    public void onClick(View view){
        if (mListener != null) {
            switch (view.getId()) {
                /*case R.id.FABPeopleSearch:
                    mListener.onFragmentInteraction("eventSearch", null, null);
                    break;

                default:
                    break;
*/
            }
        }
    }

    /**
     * creates and begins AsyncTask of PeopleWebService
     * @param v
     */
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


    /**
     * Parses the returned JSON form peopleWebService
     * loads the 3 ArrayLists with datta
     * sends to AdapterPeople
     * @param json
     */
    private void parseJSON(final String json) {
        mDataset.clear();
        peopleLocData.clear();
        peopleEmail.clear();
        JSONArray jObject = new JSONArray();
        ArrayList<String> myPeopleList = new ArrayList<String>();


        try {
            jObject = new JSONArray(json);
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject event = jObject.getJSONObject(i);

                mDataset.add(event.getString("UName"));
                peopleLocData.add(event.getString("ULocation"));
                peopleEmail.add(event.getString("UEmail"));

            }

        }catch (JSONException e){
            Log.e("JSON","People");
        }

        AdapterPeople mAdapter = new AdapterPeople(mDataset,peopleLocData,peopleEmail, jObject);
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

    /**
     * if the AsyncTask peopleWebServices returns completed
     * @param message
     */
    @Override
    public void onPeopleTaskCompletion(String message) {
        parseJSON(message);

    }

    /**
     * if the AsyncTask peopleWebServices returns with an error
     * @param error
     */
    @Override
    public void onPeopleTaskError(String error) {
        Toast.makeText( getActivity().getApplicationContext(), "An error occured while getting the EventsFragment. Try again Later",Toast.LENGTH_LONG).show();
    }
}
