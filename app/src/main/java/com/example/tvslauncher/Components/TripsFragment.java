package com.example.tvslauncher.Components;

import static com.example.tvslauncher.R.drawable.bg_light;
import static com.example.tvslauncher.R.drawable.bluetooth_bg_dark;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tvslauncher.Adapters.AlertAdapter;
import com.example.tvslauncher.Adapters.TripsAdapter;
import com.example.tvslauncher.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
public class TripsFragment extends Fragment implements TripsAdapter.ItemClickListener{

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

    private ArrayList<String> fromtxt = new ArrayList<>();
    private ArrayList<String> totxt = new ArrayList<>();
    private RecyclerView recyclerView;
    private TripsAdapter adapter;
    private MaterialCardView tripslayout;
    private TextView driver_score_info, trip_distance_info, fuel_consumption_info, trip_mileage_info, average_speed_info,
            max_speed_info, idling_info, from_txt, to_txt;

    public TripsFragment() {}

    public static TripsFragment newInstance(String param1, String param2) {
        TripsFragment fragment = new TripsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trips, container, false);
    }


    @SuppressLint("ResourceAsColor")
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_trips);
        tripslayout = view.findViewById(R.id.tripslayout);
        driver_score_info = view.findViewById(R.id.driver_score_info);
        trip_distance_info = view.findViewById(R.id.trip_distance_info);
        fuel_consumption_info = view.findViewById(R.id.fuel_consumption_info);
        trip_mileage_info = view.findViewById(R.id.trip_mileage_info);
        average_speed_info = view.findViewById(R.id.average_speed_info);
        max_speed_info = view.findViewById(R.id.max_speed_info);
        idling_info = view.findViewById(R.id.idling_info);
        from_txt = view.findViewById(R.id.from_txt);
        to_txt = view.findViewById(R.id.to_txt);

        time.add("11:00 AM - 11:45 AM");
        time.add("7:15 PM - 8:15 PM");

        date.add("Sep 28 23");
        date.add("Oct 02 23");

        duration.add("0 Hr 45 Mins");
        duration.add("1 Hr 0 Mins");

        driverscore.add("70");
        driverscore.add("85");

        tripdistance.add("8 KM");
        tripdistance.add("12 KM");

        fuelconsumption.add("0.5L");
        fuelconsumption.add("0.7L");

        tripmileage.add(String.valueOf(Math.round((8)/(0 + (45/60.0)))) + "Kmpl");
        tripmileage.add(String.valueOf(Math.round((12)/(1 + (0/60.0)))) + "Kmpl");

        avgspeed.add("45 Km/h");
        avgspeed.add("55 Km/h");

        maxspeed.add("65 Km/h");
        maxspeed.add("70 Km/h");

        idling.add("5 Min");
        idling.add("8 Min");

        fromtxt.add("J4XC+GFC, Thirumagal ByPass, Konerikarai, Kandhampatty, Tamil Nadu 636005, India");
        fromtxt.add("WH6X+RJR, Sarakki Industrial Layout, 3rd Phase,  J. P. Nagar, Bengaluru, Karnataka 560078");

        totxt.add("J4XC+GFC, Thirumagal ByPass, Konerikarai, Kandhampatty, Tamil Nadu 636005, India");
        totxt.add("5, 9th Main Rd, Sector 6, HSR Layout, Bengaluru, Karnataka 560102");

        driver_score_info.setText(driverscore.get(0));
        trip_distance_info.setText(tripdistance.get(0));
        fuel_consumption_info.setText(fuelconsumption.get(0));
        trip_mileage_info.setText(tripmileage.get(0));
        average_speed_info.setText(avgspeed.get(0));
        max_speed_info.setText(maxspeed.get(0));
        idling_info.setText(idling.get(0));
        from_txt.setText(fromtxt.get(0));
        to_txt.setText(totxt.get(0));

        adapter = new TripsAdapter(getActivity(), time, date, duration, driverscore, tripdistance, fuelconsumption, tripmileage, avgspeed, maxspeed, idling);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                tripslayout.setBackgroundDrawable(getContext().getDrawable(bluetooth_bg_dark));
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                tripslayout.setBackgroundDrawable(getContext().getDrawable(bg_light));
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:

                break;
            default:
                tripslayout.setBackgroundColor(getContext().getColor(R.color.colorPrimarygray));
        }



    }

    @Override
    public void onItemClick(View view, int position) {

        driver_score_info.setText(driverscore.get(position));
        trip_distance_info.setText(tripdistance.get(position));
        fuel_consumption_info.setText(fuelconsumption.get(position));
        trip_mileage_info.setText(tripmileage.get(position));
        average_speed_info.setText(avgspeed.get(position));
        max_speed_info.setText(maxspeed.get(position));
        idling_info.setText(idling.get(position));
        from_txt.setText(fromtxt.get(position));
        to_txt.setText(totxt.get(position));

    }
}