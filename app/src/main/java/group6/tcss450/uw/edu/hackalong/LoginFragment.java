/**
 * TCSS 450 Spring 2017 Group 6
 * LoginFragment.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import group6.tcss450.uw.edu.hackalong.tasks.LoginWebService;


/**
 * This class controls the main landing page with login credentials and login and register buttons
 */
public class LoginFragment extends Fragment implements View.OnClickListener, LoginWebService.OnLoginTaskCompleteListener {
    /* the fragment listener */
    private OnFragmentInteractionListener mListener;
    /* the email textbox */
    EditText email;
    /* the password textbox */
    EditText pwd;
    /**
     * Required empty constructor
     */
    public LoginFragment() {
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
     * This method controls the actions related to the onClickListener
     * @param v is the view for this fragment
     */
    @Override
    public void onClick(View v) {
        if (mListener != null) {
                 switch (v.getId()) {
                    case R.id.loginbutton:
                        if(email.getText().length()>0 && pwd.getText().length()>0) {
                            LoginWebService task = new LoginWebService(LoginFragment.this, email.getText().toString());
                            task.execute();
                            break;
                        }
                        break;

                    case R.id.registerbutton:
                        mListener.onFragmentInteraction("register", null, null);
                        break;

                     default:
                         break;

            }
        }
    }

    /**
     * parses the JSON recieved from LoginWebService
     * checks and compares email and password to verify.
     * calls next fragment if confirmed
     * sends values to the
     * onFragmentInteractionListener
     * @param json
     */
    private void parseJSON(final String json) {
        JSONArray mObj = null;
        try {
            mObj = new JSONArray(json);
            JSONObject userInfo = mObj.getJSONObject(0);
            String pw = userInfo.getString("UPW");

            if(pw.equals(pwd.getText().toString())){
                SharedPreferences mPref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                mPref.edit().putBoolean(getString(R.string.isLoggedIn),true).apply();

                mPref.edit().putString(getString(R.string.UE), userInfo.getString("UEmail")).apply();

                if(!userInfo.getString("UName").equals("null")){
                    mPref.edit().putString(getString(R.string.UN), userInfo.getString("UName")).apply();
                }
                if(!userInfo.getString("ULocation").equals("null")){
                    mPref.edit().putString(getString(R.string.UL), userInfo.getString("ULocation")).apply();
                }
                if(!userInfo.getString("UAge").equals("null")){
                    mPref.edit().putString(getString(R.string.UA), userInfo.getString("UAge")).apply();
                }
                if(!userInfo.getString("UEvents").equals("null")){
                    mPref.edit().putString(getString(R.string.UEv), userInfo.getString("UEvents")).apply();
                }
                if(!userInfo.getString("UBio").equals("null")){
                    mPref.edit().putString(getString(R.string.UB), userInfo.getString("UBio")).apply();
                }
                if(!userInfo.getString("UTag").equals("null")){
                    mPref.edit().putString(getString(R.string.UT), userInfo.getString("UTag")).apply();
                }

                mListener.onFragmentInteraction("events", email.getText().toString(), pw);



            }else{
                Toast.makeText( getActivity().getApplicationContext(), "The password is incorrect.",Toast.LENGTH_LONG).show();
                //throw error toast
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText( getActivity().getApplicationContext(), "The email is incorrect.",Toast.LENGTH_LONG).show();
        }




    }

    /**
     * return froma successful async task of LoginWebService
     * calls parseJSON for parsiong the return from external web service
     * @param message
     */
    @Override
    public void onLoginTaskCompletion(String message) {

            parseJSON(message);

    }

    /**
     * return from the LoginWebService async task fail
     * @param error
     */
    @Override
    public void onLoginTaskError(String error) {

    }

    /**
     * This interface holds the method used for changing fragments in the dashboard class.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String fragment, String username, String password);
    }
}
