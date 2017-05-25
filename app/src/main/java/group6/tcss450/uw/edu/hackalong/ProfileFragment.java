/**
 * TCSS 450 Spring 2017 Group 6
 * ProfileFragment.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * This class controls the user profile
 */
public class ProfileFragment extends LoginFragment {
    /* the fragment listener */
    private OnFragmentInteractionListener mListener;
    private TextView mTextView;

    TextView userName;
    TextView userAge;
    TextView userLoc;
    TextView userBio;
    TextView userEvents;
    TextView userTag;
    TextView userEmail;


    /**
     * Required empty constructor
     */
    public ProfileFragment() {
    }

    /**
     * Creates the view for the fragment
     *
     * @param inflater           inflates the fragment
     * @param container          the container that holds the fragment
     * @param savedInstanceState the previous state, if any
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        mTextView = (TextView) v.findViewById(R.id.username);

        FloatingActionButton F = (FloatingActionButton) v.findViewById(R.id.fab);
        F.setOnClickListener(this);

        userName = (TextView) v.findViewById(R.id.username);
        userEmail = (TextView) v.findViewById(R.id.email);
        userAge = (TextView) v.findViewById(R.id.age);
        userLoc = (TextView) v.findViewById(R.id.location);
        userBio = (TextView) v.findViewById(R.id.bio);
        userEvents = (TextView) v.findViewById(R.id.events);
        userTag = (TextView) v.findViewById(R.id.interests);

        SharedPreferences mPref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        userEmail.setText(mPref.getString(getString(R.string.UE), null));
        userName.setText(mPref.getString(getString(R.string.UN), null));
        String age = mPref.getString(getString(R.string.UA), null);
        if (age == null){
            userAge.setText("-1");
            //do nothing, do not update field if no data is present
        }else {
            userAge.setText(age);
        }
        userLoc.setText( mPref.getString(getString(R.string.UL), null));
        userBio.setText(mPref.getString(getString(R.string.UB), null));
        userEvents.setText(mPref.getString(getString(R.string.UEv), null));
        userTag.setText(mPref.getString(getString(R.string.UT), null));
        return v;
    }

    public void onClick(View v){
        if (mListener != null) {
            switch (v.getId()) {
                case R.id.fab:
                    mListener.onFragmentInteraction("profileEdit", null, null);
                    break;

                default:
                    break;

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
