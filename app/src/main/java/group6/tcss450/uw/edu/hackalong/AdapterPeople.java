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
 * Created by Devin on 5/25/2017.
 */

public class AdapterPeople extends RecyclerView.Adapter<AdapterPeople.MyViewHolderPeople> {
    ArrayList<String> mDataset = new ArrayList<String>();
    ArrayList<String> peopleLocData = new ArrayList<String>();
    ArrayList<String> peopleEmails = new ArrayList<String>();
    JSONArray myJSON;


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

    public AdapterPeople(ArrayList<String> myDataseta,ArrayList<String> PeopleLocDataa, ArrayList<String> PeopleEmailDat, JSONArray JSON ) {
        mDataset = myDataseta;
        peopleLocData = PeopleLocDataa;
        peopleEmails = PeopleEmailDat;
        myJSON = JSON;
    }


    @Override
    public MyViewHolderPeople onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_people, parent, false);
        // set the view's size, margins, paddings and layout parameters
        AdapterPeople.MyViewHolderPeople vh = new AdapterPeople.MyViewHolderPeople(v);
        return vh;
    }

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


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
