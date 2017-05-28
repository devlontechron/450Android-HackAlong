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

import org.json.JSONArray;
import org.json.JSONException;


/**
 * This class controls the user profile
 */
public class ProfileFragment extends LoginFragment {
    /* the fragment listener */
    private OnFragmentInteractionListener mListener;
    private TextView mTextView;
    JSONArray myJA;
    String myJAString;
    TextView userName;
    TextView userAge;
    TextView userLoc;
    TextView userBio;
    TextView userEvents;
    TextView userTag;
    TextView userEmail;
    String userE;
    String UIE;
    String uname;
    String uemail;
    String uage;
    String uloc;
    String ubio;
    String uevents;
    String utag;

    int fromPeople = 0;





    /**
     * Required empty constructor
     */
    public ProfileFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fromPeople =0;
        if (getArguments() != null) {
            userE = getArguments().getString("UE");
            myJAString = getArguments().getString("JSON");
            if (myJAString != null) {
                fromPeople = 1;
                try {
                    myJA = new JSONArray(myJAString);
                    int t = 0;

                    while (myJA.length() > t && uname == null) {
                        if (myJA.getJSONObject(t).getString("UEmail").equals(userE)) {
                            uname = myJA.getJSONObject(t).get("UName").toString();
                            uage = myJA.getJSONObject(t).get("UAge").toString();
                            uloc = myJA.getJSONObject(t).get("ULocation").toString();
                            uevents = myJA.getJSONObject(t).get("UEvents").toString();
                            utag = myJA.getJSONObject(t).get("UTag").toString();
                            ubio = myJA.getJSONObject(t).get("UBio").toString();
                        }
                        t++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }else{
                fromPeople=0;
            }
        }


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


            mTextView = (TextView) v.findViewById(R.id.profileName);

            FloatingActionButton F = (FloatingActionButton) v.findViewById(R.id.fab);
            F.setOnClickListener(this);

            userName = (TextView) v.findViewById(R.id.profileName);
            userEmail = (TextView) v.findViewById(R.id.profileEmail);
            userAge = (TextView) v.findViewById(R.id.profileAge);
            userLoc = (TextView) v.findViewById(R.id.profileLoc);
            userBio = (TextView) v.findViewById(R.id.profileBio);
            userEvents = (TextView) v.findViewById(R.id.profileEvents);
            userTag = (TextView) v.findViewById(R.id.profileIntrests);

        if (fromPeople == 0) {
            SharedPreferences mPref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

            userEmail.setText(mPref.getString(getString(R.string.UE), null));
            userName.setText(mPref.getString(getString(R.string.UN), null));
            String age = mPref.getString(getString(R.string.UA), null);
            if (age == null) {
                userAge.setText("-1");
                //do nothing, do not update field if no data is present
            } else {
                userAge.setText(age);
            }
            userLoc.setText(mPref.getString(getString(R.string.UL), null));
            userBio.setText(mPref.getString(getString(R.string.UB), null));
            userEvents.setText(mPref.getString(getString(R.string.UEv), null));
            userTag.setText(mPref.getString(getString(R.string.UT), null));

        } else {
            userName.setText(uname);
            userLoc.setText(uloc);
            userAge.setText(uage);
            userBio.setText(ubio);
            userEvents.setText(uevents);
            userTag.setText(utag);
            F.setVisibility(View.GONE);


        }

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
