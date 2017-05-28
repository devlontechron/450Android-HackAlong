package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Devin on 5/25/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    ArrayList<String> mDataset = new ArrayList<String>();
    ArrayList<String> eventLocData = new ArrayList<String>();
    ArrayList<String> eventDateData = new ArrayList<String>();
    ArrayList<String> eventIDData = new ArrayList<String>();
    private Context context;
    JSONArray myJSON;





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

    public Adapter(ArrayList<String> myDataset, ArrayList<String>  eventLocDataa, ArrayList<String>  eventDateDataa, ArrayList<String>  eventIDDataa, JSONArray JSON) {
        mDataset = myDataset;
        eventLocData = eventLocDataa;
        eventDateData = eventDateDataa;
        eventIDData = eventIDDataa;

        myJSON = JSON;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_events, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

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

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
