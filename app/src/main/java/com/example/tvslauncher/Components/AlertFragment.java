package com.example.tvslauncher.Components;

import static com.example.tvslauncher.R.drawable.bg_light;
import static com.example.tvslauncher.R.drawable.bluetooth_bg_dark;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.tvslauncher.Util.RecyclerItemClickListener;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.ArrayList;

public class AlertFragment extends Fragment implements RecyclerView.OnItemTouchListener, RecyclerItemClickListener {

    private ArrayList<Integer> alertimages = new ArrayList<>();
    private ArrayList<String> alerttext = new ArrayList<>();

    private TextView alerttxt;
    private ImageView alertimg;
    private AlertAdapter alertadapter;
    private RecyclerView alertrecycler;
    private MaterialCardView alertlayout;

    public AlertFragment() {}

    public static AlertFragment newInstance(String param1, String param2) {
        AlertFragment fragment = new AlertFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        alertimages.add(R.drawable.spanner_alert);
        alertimages.add(R.drawable.spanner_alert);
        alertimages.add(R.drawable.shield_alert);
        alertimages.add(R.drawable.battery_white);

        alerttext.add("Engine Temperature Sensor issue found");
        alerttext.add("Fuel Pump issue found");
        alerttext.add("Your vehicle next service due in 4500 km");
        alerttext.add("Your vehicle insurance policy expires on Sept 20th 2023");
        alerttext.add("Battery Voltage is too Low");

        alertadapter = new AlertAdapter(getActivity(), alerttext, alertimages, this);

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


    @Override
    public void onRecyclerItemClick(int position) {
        if (position == 0 || position == 1 || position == 3) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            final View customLayout = getLayoutInflater().inflate(R.layout.custom_alert_layout, null);
            builder.setView(customLayout);
            TextView errorCode = customLayout.findViewById(R.id.textErrorCode);
            TextView errorComponent = customLayout.findViewById(R.id.textErrorComponent);
            TextView errorDesc = customLayout.findViewById(R.id.textErrorDesc);
            if (position == 0) {
                errorCode.setText("Error Code - P1238");
                errorComponent.setText("Component affected - Engine Temperature Sensor");
                builder.setTitle("DTC error code found");
                errorDesc.setText("Short to Supply/Open circuit (Lower Limit Failure)");
            }
            if (position == 1) {
                errorCode.setText("Error Code - P0628");
                errorComponent.setText("Component affected - Fuel Pump");
                builder.setTitle("DTC error code found");
                errorDesc.setText("Fuel pump short to ground/open circuit");
            }
            if (position == 3) {
                errorCode.setText("Your Royal Sundaram insurance is about to expire");
                errorComponent.setText("Insurance expiry date - Sept 20th 2023");
                builder.setTitle("Insurance policy expiring soon");
            }
            builder.setCancelable(false);
            builder.setNegativeButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}