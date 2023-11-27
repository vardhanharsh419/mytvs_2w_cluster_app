package com.example.tvslauncher.Fragments;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tvslauncher.MainActivity;
import com.example.tvslauncher.R;

public class TopNav extends Fragment {
    private LinearLayout toplayout;
    private ImageView engine, service, engine_cooling_system, beam, battery;
    int enginecount = 0;
    private int battercount = 0;
    private int beamcount = 0;
    private int engine_cooling_system_counter = 0;
    private int servicecounter = 0;

    public TopNav() {}

    public static TopNav newInstance(String param1, String param2) {
        TopNav fragment = new TopNav();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_nav, container, false);
    }

    @SuppressLint("ResourceAsColor")
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        toplayout = view.findViewById(R.id.toplayout);
        engine = view.findViewById(R.id.engine);
        battery = view.findViewById(R.id.battery);
        beam = view.findViewById(R.id.beam);
        engine_cooling_system = view.findViewById(R.id.engine_cooling_system);
        service = view.findViewById(R.id.service);

        engine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(enginecount == 0){
                   enginecount = 1;
                   engine.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
               }
               else{
                   enginecount = 0;
                   engine.setColorFilter(Color.parseColor("#F7C515"), PorterDuff.Mode.SRC_IN);
               }

            }
        });

        battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (battercount == 0) {
                    battercount = 1;
                    battery.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                } else {
                    battercount = 0;
                    battery.setColorFilter(Color.parseColor("#F62215"), PorterDuff.Mode.SRC_IN);
                }
            }
        });


        beam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (beamcount == 0) {
                    beamcount = 1;
                    beam.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                } else {
                    beamcount = 0;
                    beam.setColorFilter(Color.parseColor("#156FF6"), PorterDuff.Mode.SRC_IN);
                }
            }
        });

        engine_cooling_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (engine_cooling_system_counter == 0) {
                    engine_cooling_system_counter = 1;
                    engine_cooling_system.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                } else {
                    engine_cooling_system_counter = 0;
                    engine_cooling_system.setColorFilter(Color.parseColor("#F62215"), PorterDuff.Mode.SRC_IN);
                }
            }
        });

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (servicecounter == 0) {
                    servicecounter = 1;
                    service.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                } else {
                    servicecounter = 0;
                    service.setColorFilter(Color.parseColor("#FBBA14"), PorterDuff.Mode.SRC_IN);
                }
            }
        });


        setmodetint();
    }

    private void setmodetint() {

        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                toplayout.setBackground(getResources().getDrawable(R.drawable.skewed_shape_rectangle));

                break;

            case Configuration.UI_MODE_NIGHT_NO:
                toplayout.setBackground(getResources().getDrawable(R.drawable.top_nav_light_bg));

                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:

                break;
            default:
                toplayout.setBackgroundColor(getContext().getColor(R.color.colorPrimarygray));
        }
    }
}