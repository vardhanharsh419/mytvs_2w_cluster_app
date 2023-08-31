package com.example.tvslauncher.Components;

import static com.example.tvslauncher.R.drawable.*;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.example.tvslauncher.Adapters.NearbyDevicesAdapter;
import com.example.tvslauncher.Adapters.PairedDevicesAdapter;
import com.example.tvslauncher.MainActivity;
import com.example.tvslauncher.R;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BluetoothFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BluetoothFrag extends Fragment implements RecyclerView.OnItemTouchListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MaterialSwitch bleswitch;
    private ArrayList<String> paireddevices = new ArrayList<>();
    private ArrayList<String> nearbydevices = new ArrayList<>();
    private RecyclerView recyclerpaired, recyclernearby;
    private PairedDevicesAdapter pairedadapter;
    private NearbyDevicesAdapter nearbyadapter;
    private CardView bluetoothlayout;
    private String mData = null;

    public BluetoothFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BluetoothFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static BluetoothFrag newInstance(String param1, String param2) {
        BluetoothFrag fragment = new BluetoothFrag();
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
        return inflater.inflate(R.layout.fragment_bluetooth, container, false);
    }

    @SuppressLint("ResourceAsColor")
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            mData= savedInstanceState.getString("Data");
        }

        BluetoothManager bluetoothManager = getActivity().getSystemService(BluetoothManager.class);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();

        bleswitch = view.findViewById(R.id.bleswitch);
        recyclerpaired = view.findViewById(R.id.paired_devices);
        recyclernearby = view.findViewById(R.id.nearby_devices);
        bluetoothlayout = view.findViewById(R.id.bluetoothlayout);

        bleswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    bleswitch.setChecked(true);
                    bleswitch.setTrackTintList(ColorStateList.valueOf(Color.GREEN));
                }
                else{
                    bleswitch.setChecked(false);
                    bleswitch.setTrackTintList(ColorStateList.valueOf(Color.WHITE));
                }
            }
        });

        paireddevices.add("Ajay Kumar");
        paireddevices.add("Ajay Kumar");
        paireddevices.add("Ajay Kumar");
        paireddevices.add("Ajay Kumar");
        paireddevices.add("Ajay Kumar");
        paireddevices.add("Ajay Kumar");
        paireddevices.add("Ajay Kumar");
        paireddevices.add("Ajay Kumar");
        paireddevices.add("Ajay Kumar");

        nearbydevices.add("Sujith");
        nearbydevices.add("Sujith");
        nearbydevices.add("Sujith");
        nearbydevices.add("Sujith");
        nearbydevices.add("Sujith");
        nearbydevices.add("Sujith");
        nearbydevices.add("Sujith");
        nearbydevices.add("Sujith");
        nearbydevices.add("Sujith");
        nearbydevices.add("Sujith");


        pairedadapter = new PairedDevicesAdapter(getActivity(), paireddevices);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerpaired.setLayoutManager(mLayoutManager);
        recyclerpaired.setAdapter(pairedadapter);


        nearbyadapter = new NearbyDevicesAdapter(getActivity(), nearbydevices);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        recyclernearby.setLayoutManager(mLayoutManager1);
        recyclernearby.setAdapter(nearbyadapter);


        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                bluetoothlayout.setBackgroundDrawable(getContext().getDrawable(bluetooth_bg_dark));
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + nightModeFlags);
        }

        bleswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if (!bluetoothAdapter.isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            bluetoothAdapter.enable();
                            bluetoothAdapter.startDiscovery();
                            return;
                        }
                        startActivityForResult(enableBtIntent, 1);
                    }else {
                        bluetoothAdapter.disable();
                    }
                }
            }
        });

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("mData", mData);
    }

}