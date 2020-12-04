package com.example.smartalarm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartalarm.R;
import com.example.smartalarm.action.Action;

import java.util.HashMap;

public class AlarmListAdapter extends
        RecyclerView.Adapter<AlarmListAdapter.ActionViewHolder> {

    private LayoutInflater mInflater;
    private HashMap<Action, AppCompatActivity> mActions;
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
            Action action = (Action) mActions.keySet().toArray()[itemPosition];
            intent.putExtra("ID", action.getID());
            mAdapter.notifyDataSetChanged();
            mContext.startActivity(intent);
            ((Activity) mContext).finish();
        }
    }

    public AlarmListAdapter(Context context, HashMap<Action, AppCompatActivity> actions) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mActions = actions;
    }

    public void setActions(HashMap<Action, AppCompatActivity> actions){
        mActions = actions;
//        for (Map.Entry<Action, AppCompatActivity> action : actions.entrySet()){
//            Log.d(AlarmListAdapter.class.getSimpleName(), action.getKey().getName());
//        }
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
        Action action = (Action) mActions.keySet().toArray()[position];
        Log.d(AlarmListAdapter.class.getSimpleName(), action.toString());
        // Add the data to the view holder.
        holder.mItemView.setText(action.getName());
    }

    @Override
    public int getItemCount() {
        return mActions.size();
    }
}
