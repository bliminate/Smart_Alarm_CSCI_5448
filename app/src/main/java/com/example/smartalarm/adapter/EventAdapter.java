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
import com.example.smartalarm.event.Event;

import java.io.Serializable;
import java.util.HashMap;

public class EventAdapter extends
      RecyclerView.Adapter<EventAdapter.ActionViewHolder> {

   private LayoutInflater mInflater;
   private HashMap<Event, AppCompatActivity> mEvents;
   private Context mContext;

   public class ActionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
      public final TextView mItemView;
      final EventAdapter mAdapter;

      public ActionViewHolder(@NonNull View itemView, EventAdapter adapter) {
         super(itemView);
         mItemView = itemView.findViewById(R.id.action);
         mAdapter = adapter;
         itemView.setOnClickListener(this);
      }

      @Override
      public void onClick(View v) {
         int itemPosition = getLayoutPosition();

         Intent intent = new Intent(mContext, mEvents.values().toArray()[itemPosition].getClass());
         //Add ID to intent
         // https://stackoverflow.com/questions/50103671/pass-values-from-recyclerview-to-view-it-in-activity
         // Retrieve the data for that position.
         Event event = (Event) mEvents.keySet().toArray()[itemPosition];
         intent.putExtra("event", (Serializable) event);
         mAdapter.notifyDataSetChanged();
         mContext.startActivity(intent);
         ((Activity) mContext).finish();
      }
   }

   public EventAdapter(Context context, HashMap<Event, AppCompatActivity> events) {
      this.mContext = context;
      this.mInflater = LayoutInflater.from(context);
      this.mEvents = events;
   }

   public void setEvents(HashMap<Event, AppCompatActivity> events){
      mEvents = events;
      notifyDataSetChanged();
   }

   @NonNull
   @Override
   public EventAdapter.ActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      // Inflate an item view.
      View itemView = mInflater.inflate(
            R.layout.actionlist_item, parent, false);
      return new EventAdapter.ActionViewHolder(itemView, this);
   }

   @Override
   public void onBindViewHolder(@NonNull EventAdapter.ActionViewHolder holder, int position) {
      // Retrieve the data for that position.
      Event mCurrent = (Event) mEvents.keySet().toArray()[position];
      // Add the data to the view holder.
      holder.mItemView.setText(mCurrent.getName());
   }

   @Override
   public int getItemCount() {
      return mEvents.size();
   }
}
