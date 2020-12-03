package com.example.smartalarm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartalarm.R;

import java.util.HashMap;

public class ActionListAdapter extends
        RecyclerView.Adapter<ActionListAdapter.ActionViewHolder> {

    private LayoutInflater mInflater;
    private HashMap<String, AppCompatActivity> mActions;
    private Context mContext;

    public class ActionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mItemView;
        final ActionListAdapter mAdapter;

        public ActionViewHolder(@NonNull View itemView, ActionListAdapter adapter) {
            super(itemView);
            mItemView = itemView.findViewById(R.id.action);
            mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();

            Intent intent = new Intent(mContext, mActions.values().toArray()[itemPosition].getClass());
            mAdapter.notifyDataSetChanged();
            mContext.startActivity(intent);
            ((Activity) mContext).finish();
        }
    }

    public ActionListAdapter(Context context, HashMap<String, AppCompatActivity> actions) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mActions = actions;
    }

    public void setActions(HashMap<String, AppCompatActivity> actions){
        mActions = actions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view.
        View itemView = mInflater.inflate(
                R.layout.actionlist_item, parent, false);
        return new ActionViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionViewHolder holder, int position) {
        // Retrieve the data for that position.
        String mCurrent = (String) mActions.keySet().toArray()[position];
        // Add the data to the view holder.
        holder.mItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mActions.size();
    }
}
