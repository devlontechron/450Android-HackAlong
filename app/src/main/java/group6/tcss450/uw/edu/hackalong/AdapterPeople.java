/**
 * TCSS 450 Spring 2017 Group 6
 * AdapterPeople.java
 * May 28, 2017
 */


package group6.tcss450.uw.edu.hackalong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;


/**
 * Class to handle creating the cards for recycler view and keeping track of the data
 */
public class AdapterPeople extends RecyclerView.Adapter<AdapterPeople.MyViewHolderPeople> {
    ArrayList<String> mDataset = new ArrayList<String>();
    ArrayList<String> peopleLocData = new ArrayList<String>();
    ArrayList<String> peopleEmails = new ArrayList<String>();
    JSONArray myJSON;

    /**
     * Inner class MYViewHolderPeople
     * identifies the card values of text views and fields
     */
    public static class MyViewHolderPeople extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextViewPName;
        public TextView mTextViewPLoc;

        public MyViewHolderPeople(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_people);
            mTextViewPName = (TextView) v.findViewById(R.id.textViewPName);
            mTextViewPLoc = (TextView) v.findViewById(R.id.textViewPLoc);

        }
    }

    /**
     * recieves passed in data from PeopleFragment class to populate the cards with the data
     * @param myDataseta
     * @param PeopleLocDataa
     * @param PeopleEmailDat
     * @param JSON
     */
    public AdapterPeople(ArrayList<String> myDataseta,ArrayList<String> PeopleLocDataa, ArrayList<String> PeopleEmailDat, JSONArray JSON ) {
        mDataset = myDataseta;
        peopleLocData = PeopleLocDataa;
        peopleEmails = PeopleEmailDat;
        myJSON = JSON;
    }

    /**
     * creates the layout of the cards in the recycler view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolderPeople onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_people, parent, false);
        // set the view's size, margins, paddings and layout parameters
        AdapterPeople.MyViewHolderPeople vh = new AdapterPeople.MyViewHolderPeople(v);
        return vh;
    }

    /**
     * actually inserts the values for the cards and binds click listeneres to each card
     * each card is kept track with setTag()
     * values of the JSON string is passed to ProfileFragment
     * value of cards selected persons Email is passed to ProfileFragments
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolderPeople holder, int position) {
        holder.mTextViewPName.setText(mDataset.get(position));
        holder.mTextViewPLoc.setText(peopleLocData.get(position));
        holder.mCardView.setTag(position);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle args;
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new ProfileFragment();
                args = new Bundle();
                args.putString("UE",peopleEmails.get((int) v.getTag()));
                args.putString("JSON",  myJSON.toString());
                myFragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, myFragment).addToBackStack(null).commit();

            }
        });
    }

    /**
     * returns the number of cards populated
     * @return
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
