/**
 * TCSS 450 Spring 2017 Group 6
 * SingleEventFragment.java
 * May 28, 2017
 */


package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SingleEventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SingleEventFragment extends LoginFragment  {

    private OnFragmentInteractionListener mListener;
    JSONArray myJA;
    String myJAString;
    String evID;
    String EN;
    String EID;
    String ELink;
    String EH;
    String ELoc;
    String ET;
    String ED;
    String EDis;


//constructor required
    public SingleEventFragment() {

    }

/**
*onCreate methode used to get arguments passed to fragments.
* arguements from Adapter.java pass the event ID and the whole JSON string
* sets String values from JSONArray object
* @param savedInstanceState
* @return View
 **/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            evID = getArguments().getString("EID");
            myJAString =  getArguments().getString("JSON");
            try {
                myJA = new JSONArray(myJAString);
                int t=0;

                //checks for event ID then stores corrosponding calues from JSON
                while(myJA.length()>t &&EID==null){
                    if(myJA.getJSONObject(t).getString("EID").equals(evID)){
                        EID = myJA.getJSONObject(t).get("EID").toString();
                        EN = myJA.getJSONObject(t).get("EName").toString();
                        ELoc = myJA.getJSONObject(t).get("ELocation").toString();
                        EH = myJA.getJSONObject(t).get("ECreator").toString();
                        ET = myJA.getJSONObject(t).get("ETag").toString();
                        ED = myJA.getJSONObject(t).get("EDate").toString();
                        EDis = myJA.getJSONObject(t).get("EDisc").toString();
                        ELink = myJA.getJSONObject(t).get("ELink").toString();

                  }
                  t++;

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    /**
    *onCreateView creates the view of the fragment
    * called by system
    * retirieves TextViews from XML fragment_single_event.xml
    * sets textViews to recieved JSON string in onCreate
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_single_event, container, false);
        TextView mNameTV= (TextView) v.findViewById(R.id.profileName);
        TextView mIDTV = (TextView) v.findViewById(R.id.profileEmail);
        TextView mHostTV = (TextView) v.findViewById(R.id.profileAge);
        TextView mLocTV = (TextView) v.findViewById(R.id.profileLoc);
        TextView mDiscTV = (TextView) v.findViewById(R.id.profileBio);
        TextView mTagTV = (TextView) v.findViewById(R.id.profileEvents);
        TextView mLinkTV = (TextView) v.findViewById(R.id.eventLink);
        TextView mDateTV = (TextView) v.findViewById(R.id.SEdate);
        mNameTV.setText(EN);
        mIDTV.setText(EID);
        mLocTV.setText(ELoc);
        mDiscTV.setText(EDis);
        mTagTV.setText(ET);
        mLinkTV.setText(ELink);
        mHostTV.setText(EH);
        mDateTV.setText(ED);
        return v;
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
