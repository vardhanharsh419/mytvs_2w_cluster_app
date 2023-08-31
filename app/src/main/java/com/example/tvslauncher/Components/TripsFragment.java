package com.example.tvslauncher.Components;

import static com.example.tvslauncher.R.drawable.bg_light;
import static com.example.tvslauncher.R.drawable.bluetooth_bg_dark;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvslauncher.Adapters.AlertAdapter;
import com.example.tvslauncher.Adapters.TripsAdapter;
import com.example.tvslauncher.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TripsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TripsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<String> time = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private ArrayList<String> duration = new ArrayList<>();
    private RecyclerView recyclerView;
    private TripsAdapter adapter;
    private MaterialCardView tripslayout;

    public TripsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TripsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TripsFragment newInstance(String param1, String param2) {
        TripsFragment fragment = new TripsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        time.add("11:03 AM - 4:09 PM");
        time.add("11:03 AM - 4:09 PM");
        date.add("Jul 11 2023");
        date.add("Jul 11 2023");
        duration.add("5 Hr 5 Mins");
        duration.add("5 Hr 5 Mins");


        adapter = new TripsAdapter(getActivity(), time, date, duration);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
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
}