/**
 * TCSS 450 Spring 2017 Group 6
 * Dashboard.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * This class is the main fragment manager and main activity. It controlls the fragment switches
 * along with initializing and filling the fragment container
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        LoginFragment.OnFragmentInteractionListener {


    Boolean loggedIn =false;
    SharedPreferences pref;



    /**
     * This method creates the activity, initalizes the toolbar, the floating action button, the
     * drawer layout, and adds the main page to the fragment container.
     * @param savedInstanceState is a previous instance of this program
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        pref = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);

        loggedIn = pref.getBoolean(getString(R.string.isLoggedIn), false);


        if(savedInstanceState == null) {
            if (findViewById(R.id.fragmentContainer) != null) {
                if(loggedIn) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragmentContainer, new EventsFragment())
                            .commit();
                }else{
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragmentContainer, new LoginFragment())
                            .commit();

                }
            }
        }
    }

    /**
     * This method controls what happens when the user presses the back button on their display
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.nav_Events:
                onFragmentInteraction("events",null,null);
                break;
            case R.id.nav_People:
                onFragmentInteraction("people",null,null);
                break;
            case R.id.nav_Profile:
                onFragmentInteraction("profile",null,null);
                break;
            case R.id.nav_Setting:
                onFragmentInteraction("settings",null,null);
                break;
            case R.id.nav_Info:
                onFragmentInteraction("info",null,null);
                break;
            default:
                //
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This method controls the changing of fragments and the passing of values between them
     * @param fragment is the name of the fragment to change to
     * @param username is the username passed, currently mostly NULL values to be implemented
     * @param password is the password passed, currently mostly NULL values to be implemented
     */
    @Override
    public void onFragmentInteraction(String fragment, String username, String password) {
        Bundle args;
        FragmentTransaction transaction;


        switch(fragment){
            case "events":
                EventsFragment events;
                events = new EventsFragment();
                args = new Bundle();
                args.putSerializable(getString(R.string.username), username);
                args.putSerializable(getString(R.string.pass), password);
                events.setArguments(args);
                transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, events)
                        .addToBackStack(null);
                transaction.commit();
                break;
            case "register":  
                RegisterFragment register;
                register = new RegisterFragment();
                args = new Bundle();
                register.setArguments(args);
                transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, register)
                        .addToBackStack(null);
                transaction.commit();
                break;
            case "people":
                PeopleFragment people = new PeopleFragment();
                args = new Bundle();
                people.setArguments(args);
                transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, people)
                        .addToBackStack(null);
                transaction.commit();
                break;

            case "profile":
                loggedIn = pref.getBoolean(getString(R.string.isLoggedIn), false);

                if(loggedIn) {
                    ProfileFragment profile = new ProfileFragment();
                    args = new Bundle();
                    profile.setArguments(args);
                    transaction = getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, profile)
                            .addToBackStack(null);
                    transaction.commit();
                }else{
                    onFragmentInteraction("login",null,null);
                }
                break;

            case "settings":
                SettingFragment settings = new SettingFragment();
                args = new Bundle();
                settings.setArguments(args);
                transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, settings)
                        .addToBackStack(null);
                transaction.commit();
                break;

            case "info":
                AppInfoFragment info;
                info = new AppInfoFragment();
                args = new Bundle();
                info.setArguments(args);
                transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, info)
                        .addToBackStack(null);
                transaction.commit();
                break;

            case "profileEdit":
                EditProfileFragment EP = new EditProfileFragment();
                args = new Bundle();
                EP.setArguments(args);
                transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, EP)
                        .addToBackStack(null);
                transaction.commit();
                break;

            case "eventSearch":
                SearchFragment search = new SearchFragment();
                args = new Bundle();
                search.setArguments(args);
                transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, search)
                        .addToBackStack(null);
                transaction.commit();
                break;

            case "login":
                LoginFragment login = new LoginFragment();
                args = new Bundle();
                login.setArguments(args);
                transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, login)
                        .addToBackStack(null);
                transaction.commit();
                break;

        }
    }
}
