package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import group6.tcss450.uw.edu.hackalong.tasks.EditProfileWebService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EditProfileFragment extends ProfileFragment implements EditProfileWebService.OnEditTaskCompleteListener {


    private OnFragmentInteractionListener mListener;
    EditText userName;
    EditText userAge;
    EditText userLoc;
    EditText userBio;
    EditText userEvents;
    EditText userTag;

    String un;
    int ua;
    String ul;
    String ub;
    String ue;
    String ut;
    String uev;


    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        Button b = (Button) v.findViewById(R.id.savebutton);
        b.setOnClickListener(this);
        userName = (EditText) v.findViewById(R.id.editusername);
        userAge = (EditText) v.findViewById(R.id.editage);
        userLoc = (EditText) v.findViewById(R.id.editlocation);
        userBio = (EditText) v.findViewById(R.id.editbio);
        userEvents = (EditText) v.findViewById(R.id.editevents);
        userTag = (EditText) v.findViewById(R.id.editinterests);

        return v;
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


    @Override
    public void onClick(View v) {
        if (mListener != null) {
            un = userName.getText().toString();
            ua = Integer.parseInt(userAge.getText().toString());
            ul = userLoc.getText().toString();
            ub = userBio.getText().toString();
            ut = userTag.getText().toString();
            uev = userEvents.getText().toString();
            //TODO get userEmial from file
            ue = "dummy";
            EditProfileWebService task = new EditProfileWebService(EditProfileFragment.this, ue, un, ul, ua, uev, ub, ut);
            task.execute();
            //mListener.onFragmentInteraction("events", username, password);

        }
    }


    private void parseRegJSON(String message) {
        if (message.equals("1")) {
            mListener.onFragmentInteraction("profile", null, null);
            Toast.makeText(getActivity().getApplicationContext(), "Edits have been saved", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getActivity().getApplicationContext(), "Error " + message, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onEditTaskCompletion(String message) {
        parseRegJSON(message);

    }

    @Override
    public void onEditTaskError(String error) {
        Toast.makeText(getActivity().getApplicationContext(), "An error with the web service has occured. Try again later.", Toast.LENGTH_LONG).show();
    }
}
