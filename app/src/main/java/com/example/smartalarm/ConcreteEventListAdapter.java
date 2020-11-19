package com.example.smartalarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConcreteEventListAdapter extends
        RecyclerView.Adapter<ConcreteEventListAdapter.ConcreteEventViewHolder> {

    private LayoutInflater mInflater;
    private List<Event> mEvents;
    private Context mContext;

    public class ConcreteEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mItemView;
        final ConcreteEventListAdapter mAdapter;

        public ConcreteEventViewHolder(@NonNull View itemView, ConcreteEventListAdapter adapter) {
            super(itemView);
            mItemView = itemView.findViewById(R.id.concreteEvent);
            mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();

            Event event = mEvents.get(itemPosition);
            event.activated = !event.activated;
            mAdapter.notifyDataSetChanged();
            // TODO: Set On of Off
        }
    }

    public ConcreteEventListAdapter(Context context, List<Event> events) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mEvents = events;
    }

    @NonNull
    @Override
    public ConcreteEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view.
        View itemView = mInflater.inflate(
                R.layout.concreteeventlist_item, parent, false);
        return new ConcreteEventViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ConcreteEventViewHolder holder, int position) {
        // Retrieve the data for that position.
        String mCurrent = mEvents.get(position).name;
        // Add the data to the view holder.
        holder.mItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }
}
