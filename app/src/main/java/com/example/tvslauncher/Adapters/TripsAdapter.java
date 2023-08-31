package com.example.tvslauncher.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tvslauncher.R;

import java.util.ArrayList;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.MyViewHolder> {
    private Context doc_context;
    private ArrayList<String> time = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private ArrayList<String> duration = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView time, date, duration;
        public ImageView doc_thumbnail;

        public MyViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time);
            date = view.findViewById(R.id.date);
            duration = view.findViewById(R.id.duration);

        }
    }


    public TripsAdapter(Context context, ArrayList<String> time, ArrayList<String> date, ArrayList<String> duration) {
        this.doc_context = context;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    @Override
    public TripsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trips_adapter, parent, false);

        return new TripsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TripsAdapter.MyViewHolder holder, final int position) {
        holder.time.setText(time.get(position));
        holder.date.setText(date.get(position));
        holder.duration.setText(duration.get(position));

    }

    @Override
    public int getItemCount() {
        return time.size();
    }

//    public void removeItem(int position) {
//        cartList.remove(position);
//        // notify the item removed by position
//        // to perform recycler view delete animations
//        // NOTE: don't call notifyDataSetChanged()
//        notifyItemRemoved(position);
//    }
//
//    public void restoreItem(doctors_item item, int position) {
//        cartList.add(position, item);
//        // notify item added by position
//        notifyItemInserted(position);
//    }
//
//    public void filterList(List<doctors_item> filterdNames) {
//        this.cartList = filterdNames;
//        notifyDataSetChanged();
//    }
}