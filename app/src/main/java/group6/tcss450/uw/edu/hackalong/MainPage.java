/**
 * TCSS 450 Spring 2017 Group 6
 * MainPage.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * This class controls the main landing page with login credentials and login and register buttons
 */
public class MainPage extends Fragment implements View.OnClickListener{
    /* the fragment listener */
    private OnFragmentInteractionListener mListener;
    /* the email textbox */
    EditText email;
    /* the password textbox */
    EditText pwd;
    /**
     * Required empty constructor
     */
    public MainPage() {
    }

    /**
     * This method inflates the fragment and creates the buttons included, then attaches listeners
     * @param inflater inflates the fragment
     * @param container the container that holds the fragment
     * @param savedInstanceState the previous state, if any
     * @return returns the inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_page, container, false);
        Button b = (Button) v.findViewById(R.id.loginbutton);
        b.setOnClickListener(this);
        b = (Button) v.findViewById(R.id.registerbutton);
        b.setOnClickListener(this);
        email = (EditText) v.findViewById(R.id.loginemail);
        pwd = (EditText) v.findViewById(R.id.loginpassword);
        return v;
    }

    /**
     * This method checks the context and sets the mListener if successful
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
     * This method calls the super method and sets the mListener to null
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This method controls the actions related to the onClickListener and sends values to the
     * onFragmentInteractionListener
     * @param v is the view for this fragment
     */
    @Override
    public void onClick(View v) {
        if (mListener != null) {
            if (email.getText().length() > 0 && pwd.getText().length() > 0) {
                switch (v.getId()) {
                    case R.id.loginbutton:
                        mListener.onFragmentInteraction("events", email.getText().toString(), pwd.getText().toString());
                        break;
                    case R.id.registerbutton:
                        mListener.onFragmentInteraction("register", null, null);
                        break;
                }
            }
        }
    }

    /**
     * This interface holds the method used for changing fragments in the dashboard class.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String fragment, String username, String password);
    }
}
