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
import android.widget.TextView;
import android.widget.Toast;

import group6.tcss450.uw.edu.hackalong.tasks.PeopleWebService;


/**
 * This class is unimplemented, but will display a list of people.
 */
public class PeopleFragment extends LoginFragment implements PeopleWebService.OnPeopleTaskCompleteListener {
    /* the fragment listener */
    private OnFragmentInteractionListener mListener;
    private TextView mTextView;
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
        View v = inflater.inflate(R.layout.fragment_people, container, false);
        mTextView = (TextView) v.findViewById(R.id.people1);
        onClick(v);
        return v;

    }


    @Override
    public void onClick(View view){
        PeopleWebService task = new PeopleWebService(PeopleFragment.this);
        task.execute();
        //updateContent(JSONRecieved);
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

        mTextView.setText(json);
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
