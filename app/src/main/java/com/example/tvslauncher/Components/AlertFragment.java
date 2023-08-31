package com.example.tvslauncher.Components;

import static com.example.tvslauncher.R.drawable.bg_light;
import static com.example.tvslauncher.R.drawable.bluetooth_bg_dark;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tvslauncher.Adapters.AlertAdapter;
import com.example.tvslauncher.Adapters.PairedDevicesAdapter;
import com.example.tvslauncher.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlertFragment extends Fragment implements RecyclerView.OnItemTouchListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Integer> alertimages = new ArrayList<>();
    private ArrayList<String> alerttext = new ArrayList<>();

    private TextView alerttxt;
    private ImageView alertimg;
    private AlertAdapter alertadapter;
    private RecyclerView alertrecycler;
    private MaterialCardView alertlayout;

    public AlertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlertFragment newInstance(String param1, String param2) {
        AlertFragment fragment = new AlertFragment();
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
        return inflater.inflate(R.layout.fragment_alert, container, false);
    }

    @SuppressLint("ResourceAsColor")
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        alertimg = view.findViewById(R.id.alerticon);
        alerttxt = view.findViewById(R.id.alertstxt);
        alertrecycler = view.findViewById(R.id.alertrecycler);
        alertlayout = view.findViewById(R.id.alertlayout);

        alertimages.add(R.drawable.spanner_alert);
        alertimages.add(R.drawable.shield_alert);
        alertimages.add(R.drawable.battery_white);

        alerttext.add("Your vehicle next service due on 1500 km");
        alerttext.add("Your vehicle insurance policy expires on Aug 20th 2023");
        alerttext.add("Battery Voltage is too Low");

        alertadapter = new AlertAdapter(getActivity(), alerttext, alertimages);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        alertrecycler.setLayoutManager(mLayoutManager);
        alertrecycler.setAdapter(alertadapter);


        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                alertlayout.setBackgroundDrawable(getContext().getDrawable(bluetooth_bg_dark));
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                alertlayout.setBackgroundDrawable(getContext().getDrawable(bg_light));
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:

                break;
            default:
                alertlayout.setBackgroundColor(getContext().getColor(R.color.colorPrimarygray));
        }


    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}