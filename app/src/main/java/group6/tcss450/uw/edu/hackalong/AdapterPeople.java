package group6.tcss450.uw.edu.hackalong;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Devin on 5/25/2017.
 */

public class AdapterPeople extends RecyclerView.Adapter<AdapterPeople.MyViewHolderPeople> {
    ArrayList<String> mDataset = new ArrayList<String>();
    ArrayList<String> peopleLocData = new ArrayList<String>();
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

    public AdapterPeople(ArrayList<String> myDataseta,ArrayList<String> PeopleLocDataa) {
        mDataset = myDataseta;
        peopleLocData = PeopleLocDataa;
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

    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
