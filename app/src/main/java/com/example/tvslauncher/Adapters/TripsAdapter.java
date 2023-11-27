package com.example.tvslauncher.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tvslauncher.Components.TripsFragment;
import com.example.tvslauncher.R;

import java.util.ArrayList;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.MyViewHolder> {
    private Context doc_context;
    private ArrayList<String> time = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private ArrayList<String> duration = new ArrayList<>();
    private ArrayList<String> driverscore = new ArrayList<>();
    private ArrayList<String> tripdistance = new ArrayList<>();
    private ArrayList<String> fuelconsumption = new ArrayList<>();
    private ArrayList<String> tripmileage = new ArrayList<>();
    private ArrayList<String> avgspeed = new ArrayList<>();
    private ArrayList<String> maxspeed = new ArrayList<>();
    private ArrayList<String> idling = new ArrayList<>();

    private ItemClickListener mClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView time, date, duration;
        public ImageView doc_thumbnail;

        public MyViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time);
            date = view.findViewById(R.id.date);
            duration = view.findViewById(R.id.duration);


            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());

        }


    }

    public TripsAdapter(Context context, ArrayList<String> time, ArrayList<String> date, ArrayList<String> duration,
                        ArrayList<String> driverscore, ArrayList<String> tripdistance, ArrayList<String> fuelconsumption,
                        ArrayList<String> tripmileage, ArrayList<String> avgspeed, ArrayList<String> maxspeed, ArrayList<String> idling) {
        this.doc_context = context;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.driverscore = driverscore;
        this.tripdistance = tripdistance;
        this.fuelconsumption = fuelconsumption;
        this.tripmileage = tripmileage;
        this.avgspeed = avgspeed;
        this.maxspeed = maxspeed;
        this.idling = idling;
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

    String getItem(int id) {
        return time.get(id);
    }

    public void setClickListener(TripsFragment itemClickListener) {
        this.mClickListener = (ItemClickListener) itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}