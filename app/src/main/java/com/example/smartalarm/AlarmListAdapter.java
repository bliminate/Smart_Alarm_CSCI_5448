package com.example.smartalarm;

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
import com.example.smartalarm.dataStructures.NameIdPair;

import java.util.HashMap;

public class AlarmListAdapter extends
        RecyclerView.Adapter<AlarmListAdapter.ActionViewHolder> {

    private LayoutInflater mInflater;
    private HashMap<NameIdPair, AppCompatActivity> mActions;
    private Context mContext;

    public class ActionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mItemView;
        final AlarmListAdapter mAdapter;

        public ActionViewHolder(@NonNull View itemView, AlarmListAdapter adapter) {
            super(itemView);
            mItemView = itemView.findViewById(R.id.action);
            mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();

            Intent intent = new Intent(mContext, mActions.values().toArray()[itemPosition].getClass());
            //Add ID to intent
            // https://stackoverflow.com/questions/50103671/pass-values-from-recyclerview-to-view-it-in-activity
            // Retrieve the data for that position.
            NameIdPair nPair = (NameIdPair) mActions.keySet().toArray()[itemPosition];
            intent.putExtra("ID", nPair.ID);
            mAdapter.notifyDataSetChanged();
            mContext.startActivity(intent);
            ((Activity) mContext).finish();
        }
    }

    public AlarmListAdapter(Context context, HashMap<NameIdPair, AppCompatActivity> actions) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mActions = actions;
    }

    public void setActions(HashMap<NameIdPair, AppCompatActivity> actions){
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
        NameIdPair mCurrent = (NameIdPair) mActions.keySet().toArray()[position];
        // Add the data to the view holder.
        holder.mItemView.setText(mCurrent.Name);
    }

    @Override
    public int getItemCount() {
        return mActions.size();
    }
}
