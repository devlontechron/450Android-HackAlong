/**
 * TCSS 450 Spring 2017 Group 6
 * Dashboard.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * This class is the main fragment manager and main activity. It controlls the fragment switches
 * along with initializing and filling the fragment container
 */
public class DashBoardActivity extends AppCompatActivity
        implements
        MainPageFragment.OnFragmentInteractionListener {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;


    /**
     * This method creates the activity, initalizes the toolbar, the floating action button, the
     * drawer layout, and adds the main page to the fragment container.
     * @param savedInstanceState is a previous instance of this program
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        nvDrawer = (NavigationView) findViewById(R.id.nav_view);
        //nvDrawer.setNavigationItemSelectedListener(this);
        setupDrawerContent(nvDrawer);


        if(savedInstanceState == null) {
            if (findViewById(R.id.fragmentContainer) != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, new MainPageFragment())
                        .commit();
            }
        }
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_manage:
                fragmentClass = SettingFragment.class;
                break;
            case R.id.nav_app_info:
                fragmentClass = AppInfoFragment.class;
                break;

            default:
                fragmentClass = AppInfoFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
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

    /**
     * This method creates the options menu
     * @param menu is the options menu to be inflated
     * @return returns true once the menu is created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    /**
     * This method controls what happens when a user clicks on an item in the options menu
     * @param item the item that was clicked on
     * @return returns true if the item is in the action_settings, calls the super method otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * This method controls the changing of fragments and the passing of values between them
     * @param fragment is the name of the fragment to change to
     * @param username is the username passed, currently mostly NULL values to be implemented
     * @param password is the password passed, currently mostly NULL values to be implemented
     */
    @Override
    public void onFragmentInteraction(String fragment, String username, String password) {
        if (fragment.equals("events")) {
            EventsFragment events;
            events = new EventsFragment();
            Bundle args = new Bundle();
            args.putSerializable(getString(R.string.username), username);
            args.putSerializable(getString(R.string.pass), password);
            events.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, events)
                    .addToBackStack(null);
            transaction.commit();
        } else if (fragment.equals("register")) {
            RegisterFragment register;
            register = new RegisterFragment();
            Bundle args = new Bundle();
            register.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, register)
                    .addToBackStack(null);
            transaction.commit();
        }
    }
}
