/**
 * TCSS 450 Spring 2017 Group 6
 * Adapter.java
 * May 28, 2017
 */

package group6.tcss450.uw.edu.hackalong;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONArray;
import java.util.ArrayList;


/**
 * @author Devin
 * Adapter class to handle making the cards and their data for the eventsFrgament
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    ArrayList<String> mDataset = new ArrayList<String>();
    ArrayList<String> eventLocData = new ArrayList<String>();
    ArrayList<String> eventDateData = new ArrayList<String>();
    ArrayList<String> eventIDData = new ArrayList<String>();
    private Context context;
    JSONArray myJSON;


    /**
     * Inner class MYViewHolder
     * handles finding and creating the variable for the cards fields
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextViewEvName;
        public TextView mTextViewEvLoc;
        public TextView mTextViewEvDate;
        public LinearLayout myLay;
        private View myV;

        public MyViewHolder(View v) {
            super(v);
            myV = v;
            mCardView = (CardView) v.findViewById(R.id.cardviewr);
            mTextViewEvName = (TextView) v.findViewById(R.id.textViewName);
            mTextViewEvLoc = (TextView) v.findViewById(R.id.textViewLoc);
            mTextViewEvDate = (TextView) v.findViewById(R.id.TextViewDate);
        }
    }

    /**
     * recieves data from EventsFragment after JSON asyncTask EventsWebService
     * @param myDataset
     * @param eventLocDataa
     * @param eventDateDataa
     * @param eventIDDataa
     * @param JSON
     */
    public Adapter(ArrayList<String> myDataset, ArrayList<String>  eventLocDataa, ArrayList<String>  eventDateDataa, ArrayList<String>  eventIDDataa, JSONArray JSON) {
        mDataset = myDataset;
        eventLocData = eventLocDataa;
        eventDateData = eventDateDataa;
        eventIDData = eventIDDataa;

        myJSON = JSON;
    }

    /**
     * creates the vies for the cards into the recycler view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_events, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    /**
     * Creates each card bassed off of the recieved dat inside mDataset and 2 others
     * Sets a click listener to each card which keeps track of location of information in the
     * arrayLists and sends to ProfileFragments
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Adapter.MyViewHolder holder, final int position) {
        holder.mTextViewEvName.setText(mDataset.get(position));
        holder.mTextViewEvLoc.setText(eventLocData.get(position));
        holder.mTextViewEvDate.setText(eventDateData.get(position));
        holder.mCardView.setTag(position);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle args;
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new SingleEventFragment();
                args = new Bundle();
                args.putString("EID",eventIDData.get((int) v.getTag()));
                args.putString("JSON",  myJSON.toString());
                myFragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, myFragment).addToBackStack(null).commit();

            }
        });
    }

    /**
     * returns count of cards diplayed
     * @return int
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
