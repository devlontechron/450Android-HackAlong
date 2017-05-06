/**
 * TCSS 450 Spring 2017 Group 6
 * Dashboard.java
 * May 5, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
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
public class DashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainPage.OnFragmentInteractionListener {

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

        if(savedInstanceState == null) {
            if (findViewById(R.id.fragmentContainer) != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, new MainPage())
                        .commit();
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method controls what happens when someone selects something in the items menu. It is
     * not yet implemented.
     * @param item is the item that was clicked on
     * @return returns true always since not implemented
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
         if (id == R.id.nav_manage) {
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
        if (fragment.equals("events")) {
            Events events;
            events = new Events();
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
            Register register;
            register = new Register();
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
