package com.example.tvslauncher.Components;

import static android.content.DialogInterface.BUTTON_POSITIVE;
import static com.example.tvslauncher.R.drawable.*;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tvslauncher.Adapters.NearbyDevicesAdapter;
import com.example.tvslauncher.Adapters.PairedDevicesAdapter;
import com.example.tvslauncher.Bluetooth.BleDevices;
import com.example.tvslauncher.Bluetooth.BluetoothChatService;
import com.example.tvslauncher.Bluetooth.Constants;
import com.example.tvslauncher.Bluetooth.DeviceData;
import com.example.tvslauncher.Bluetooth.Global;
import com.example.tvslauncher.Bluetooth.Permission;
import com.example.tvslauncher.R;
import com.example.tvslauncher.ViewModels.PairedBluetoothDevices;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BluetoothFrag extends Fragment implements RecyclerView.OnItemTouchListener {
    private static final int REQUEST_ENABLE_BT = 123;
    private MaterialSwitch bleswitch;
    private ArrayList<String> paireddevicesname = new ArrayList<>();
    private ArrayList<String> paireddevicesaddress = new ArrayList<>();
    private ArrayList<BluetoothDevice> pairedevicesinfo = new ArrayList<BluetoothDevice>();
    ArrayList<String> nearbydevices = new ArrayList<>();
    ArrayList<BluetoothDevice> nearbydevicesinfo = new ArrayList<BluetoothDevice>();
    private RecyclerView recyclerpaired, recyclernearby;
    private PairedDevicesAdapter pairedadapter;
    private NearbyDevicesAdapter nearbyadapter;
    private CardView bluetoothlayout;
    private String mData = null;
    public ListView nearby_devices, paired_devices;
    private BleDevices bleDevices;
    ArrayAdapter adapter;
    PairedBluetoothDevices pairedBluetoothDevices;
    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedD;
    BluetoothDevice device;
    private boolean mIsRunningSuccess;
    private Handler handler;
    BluetoothDevice d;

    ConstraintLayout mainconstraint;

    private ArrayList<DeviceData> mDeviceList = new ArrayList<DeviceData>();

    private Global global;
    private Permission permission = new Permission();
    private boolean alreadyAskedForPermission = false;
    private String PERMISSION_REQUEST_LOCATION_KEY = "PERMISSION_REQUEST_LOCATION";
    private BluetoothChatService mChatService;
    ArrayList<DeviceData> mPairedDeviceList = new ArrayList<DeviceData>();

    public BluetoothFrag() {
        // Required empty public constructor
    }

    public static BluetoothFrag newInstance(String param1, String param2) {
        BluetoothFrag fragment = new BluetoothFrag();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bluetooth, container, false);
    }

    @SuppressLint({"ResourceAsColor", "MissingPermission"})
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            mData = savedInstanceState.getString("Data");
        }

        bluetoothManager = getContext().getSystemService(BluetoothManager.class);
        bluetoothAdapter = bluetoothManager.getAdapter();

        if (savedInstanceState != null)alreadyAskedForPermission = savedInstanceState.getBoolean(PERMISSION_REQUEST_LOCATION_KEY, false);

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getContext().registerReceiver(mReceiver, filter);

        // Register fom
        // r broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        getContext().registerReceiver(mReceiver, filter);

        findDevices();

        paired_devices = view.findViewById(R.id.paired_devices);
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, mPairedDeviceList);
        paired_devices.setAdapter(adapter);

        nearby_devices = view.findViewById(R.id.nearby_devices);
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, mDeviceList);
        nearby_devices.setAdapter(adapter);

        bleswitch = view.findViewById(R.id.bleswitch);
//        recyclerpaired = view.findViewById(R.id.paired_devices);
        mainconstraint = view.findViewById(R.id.mainconstraint);
        bluetoothlayout = view.findViewById(R.id.bluetoothlayout);

        paired_devices.setClickable(true);
        nearby_devices.setClickable(true);

        bleswitch.setTrackTintList(ColorStateList.valueOf(Color.GREEN));
        bleswitch.setChecked(true);

        bleswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                  bleswitch.setTrackTintList(ColorStateList.valueOf(Color.GREEN));
                }
                else{
                    if (bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.disable();
                        bleswitch.setTrackTintList(ColorStateList.valueOf(Color.WHITE));

                        Toast.makeText(getContext(), "Turning off Bluetooth...", Toast.LENGTH_SHORT).show();

                        paired_devices.setClickable(false);
                        nearby_devices.setClickable(false);
                        adapter.clear();
                        paireddevicesaddress.clear();
                        paireddevicesname.clear();
                        pairedevicesinfo.clear();
                        nearbydevices.clear();
                        nearbydevicesinfo.clear();

                    }
                }
            }
        });


//        pairedadapter = new PairedDevicesAdapter(getActivity(), paireddevices);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerpaired.setLayoutManager(mLayoutManager);
//        recyclerpaired.setAdapter(pairedadapter);

//        nearbyadapter = new NearbyDevicesAdapter(getActivity(), nearbydevices);
//        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
//        recyclernearby.setLayoutManager(mLayoutManager1);
//        recyclernearby.setAdapter(nearbyadapter);



        paired_devices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                connectDevice(mDeviceList.get(position));
            }
        });

//        nearby_devices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("afasfsa", String.valueOf(nearbydevices.get(position)));
//            }
//        });


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


        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                mPairedDeviceList.add(new DeviceData(deviceName,deviceHardwareAddress));
                adapter.notifyDataSetChanged();
            }

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("mData", mData);
    }

    private void findDevices() {

        checkPermissions();
    }

    private void checkPermissions() {

        if (alreadyAskedForPermission) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {

                AlertDialog builder = new AlertDialog.Builder(getContext()).create();
                builder.setTitle("need_loc_access");
                builder.setMessage("please_grant_loc_access");
                builder.setButton(BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alreadyAskedForPermission = true;
                        permission.askForPermissions(getContext());
                    }
                });
                builder.show();

            } else {
                startDiscovery();
            }
        } else {
            startDiscovery();
            alreadyAskedForPermission = true;
        }

    }

    @SuppressLint("MissingPermission")
    private void startDiscovery() {

        paireddevicesaddress.clear();
        paireddevicesname.clear();
        pairedevicesinfo.clear();
        nearbydevices.clear();
        nearbydevicesinfo.clear();
        mDeviceList.clear();

        if (bluetoothAdapter.isDiscovering() == false)bluetoothAdapter.cancelDiscovery();

        bluetoothAdapter.startDiscovery();
    }


    @SuppressLint("MissingPermission")
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND == action) {
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address

                DeviceData deviceData = new DeviceData(deviceName, deviceHardwareAddress);
                mDeviceList.add(deviceData);

                HashSet setList = new HashSet<DeviceData>(mDeviceList);
                mDeviceList.clear();
                mDeviceList.addAll(setList);
                adapter.notifyDataSetChanged();
            }

            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {}
        }
    };
    @SuppressLint("MissingPermission")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_OK) {
           Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            ArrayList<DeviceData> mPairedDeviceList = new ArrayList<DeviceData>();

            mPairedDeviceList.clear();

            if (pairedDevices.size()  >= 0) {
                for (BluetoothDevice device : pairedDevices) {
                    String deviceName = device.getName();
                    String deviceHardwareAddress = device.getAddress(); // MAC address
                    mPairedDeviceList.add(new DeviceData(deviceName,deviceHardwareAddress));
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void connectDevice(DeviceData deviceData) {

        bluetoothAdapter.cancelDiscovery();
        String deviceAddress = deviceData.getDeviceHardwareAddress();

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceAddress);

        Toast.makeText(getContext(), "Connecting...", Toast.LENGTH_LONG).show();

        mChatService.connect(device, true);

    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(mReceiver);
    }

}