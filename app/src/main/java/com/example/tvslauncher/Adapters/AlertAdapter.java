package com.example.tvslauncher.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tvslauncher.R;
import com.example.tvslauncher.Util.RecyclerItemClickListener;

import java.util.ArrayList;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.MyViewHolder> {
    private Context doc_context;
    private ArrayList<String> alerts = new ArrayList<>();
    private ArrayList<Integer> alertimage = new ArrayList<>();
    private RecyclerItemClickListener itemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView alertimg;
        public LinearLayout alertCard;
        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.alerttxt);
            alertimg = view.findViewById(R.id.alerticon);
            alertCard = view.findViewById(R.id.alertCard);
        }
    }


    public AlertAdapter(Context context, ArrayList<String> alertstxt, ArrayList<Integer> alertimage, RecyclerItemClickListener itemClickListener) {
        this.doc_context = context;
        this.alerts = alertstxt;
        this.alertimage = alertimage;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public AlertAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alert_adapter, parent, false);

        return new AlertAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlertAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(alerts.get(position));
        holder.alertCard.setOnClickListener(v -> {
            itemClickListener.onRecyclerItemClick(position);
        });

        Glide.with(doc_context)
                .load(alertimage.get(position))
                .into(holder.alertimg);

    }

    @Override
    public int getItemCount() {
        return alerts.size();
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
