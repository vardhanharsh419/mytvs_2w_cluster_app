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

public class NearbyDevicesAdapter extends RecyclerView.Adapter<NearbyDevicesAdapter.MyViewHolder> {
    private Context doc_context;
    private ArrayList<String> nearbydevices = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView doc_thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.nearbydevices);

        }
    }


    public NearbyDevicesAdapter(Context context, ArrayList<String> nearbydevices) {
        this.doc_context = context;
        this.nearbydevices = nearbydevices;
    }

    @Override
    public NearbyDevicesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nearbydevicesadapter, parent, false);

        return new NearbyDevicesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NearbyDevicesAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(nearbydevices.get(position));

    }

    @Override
    public int getItemCount() {
        return nearbydevices.size();
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