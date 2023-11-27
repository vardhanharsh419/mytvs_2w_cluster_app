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

public class PairedDevicesAdapter extends RecyclerView.Adapter<PairedDevicesAdapter.MyViewHolder> {
    private Context doc_context;
    private ArrayList<String> paireddevices = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView doc_thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.pairednames);

        }
    }


    public PairedDevicesAdapter(Context context, ArrayList<String> pairedevices) {
        this.doc_context = context;
        this.paireddevices = pairedevices;
    }

    @Override
    public PairedDevicesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.paireddevicesadapter, parent, false);

        return new PairedDevicesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PairedDevicesAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(paireddevices.get(position));

    }

    @Override
    public int getItemCount() {
        return paireddevices.size();
    }

}