package com.example.tvslauncher.Components;

import static com.example.tvslauncher.R.drawable.bg_light;
import static com.example.tvslauncher.R.drawable.bluetooth_bg_dark;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.example.tvslauncher.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.slider.Slider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MaterialCardView settingslayout;

    private View display, bluetooth, documents, about;
    private MaterialCardView displaylean, bluetoothlean, documentslean, aboutlean;
    public Button lightbutton, darkbutton, autobutton;
    public MaterialSwitch bleswitch, callswitch, musicswitch;
    public Slider brigthnesslider;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }


    @SuppressLint("ResourceAsColor")
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        settingslayout = view.findViewById(R.id.settings_frag);
        display = view.findViewById(R.id.displaylayout);
        bluetooth = view.findViewById(R.id.blelayout);
        documents = view.findViewById(R.id.documentlayout);
        about = view.findViewById(R.id.aboutlayout);
        displaylean = view.findViewById(R.id.displaylean);
        bluetoothlean = view.findViewById(R.id.blelean);
        documentslean = view.findViewById(R.id.documentslean);
        aboutlean = view.findViewById(R.id.aboutlean);

        lightbutton = view.findViewById(R.id.lightButton);
        darkbutton = view.findViewById(R.id.darkButton);
        autobutton = view.findViewById(R.id.autoButton);
        brigthnesslider = view.findViewById(R.id.brightness_slider);

        bleswitch = view.findViewById(R.id.blutoothswitch);
        callswitch = view.findViewById(R.id.callswitch);
        musicswitch = view.findViewById(R.id.musicswitch);

        display.setVisibility(View.VISIBLE);
        bluetooth.setVisibility(View.GONE);
        about.setVisibility(View.GONE);
        documents.setVisibility(View.GONE);

        displaylean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setVisibility(View.VISIBLE);
                bluetooth.setVisibility(View.GONE);
                about.setVisibility(View.GONE);
                documents.setVisibility(View.GONE);

//                displaylean.setChecked(false);

                displaylean.setCardBackgroundColor(Color.WHITE);
            }
        });

        bluetoothlean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setVisibility(View.GONE);
                bluetooth.setVisibility(View.VISIBLE);
                about.setVisibility(View.GONE);
                documents.setVisibility(View.GONE);

                bluetoothlean.setChecked(true);
                displaylean.setChecked(false);
                aboutlean.setChecked(false);
                documentslean.setChecked(false);

//                bluetoothlean.setCardBackgroundColor(Color.WHITE);

            }
        });

        documentslean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setVisibility(View.GONE);
                bluetooth.setVisibility(View.GONE);
                about.setVisibility(View.GONE);
                documents.setVisibility(View.VISIBLE);

                documentslean.setCardBackgroundColor(R.color.white);

            }
        });

        aboutlean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setVisibility(View.GONE);
                bluetooth.setVisibility(View.GONE);
                about.setVisibility(View.VISIBLE);
                documents.setVisibility(View.GONE);

                aboutlean.setCardBackgroundColor(Color.WHITE);

            }
        });


        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                settingslayout.setBackgroundDrawable(getContext().getDrawable(bluetooth_bg_dark));
                darkbutton.setTextColor(getResources().getColor(R.color.white));
                darkbutton.setBackgroundColor(getResources().getColor(R.color.colordarkblue));

                break;

            case Configuration.UI_MODE_NIGHT_NO:
                lightbutton.setBackgroundColor(getResources().getColor(R.color.colordarkblue));
                lightbutton.setTextColor(getResources().getColor(R.color.white));
                settingslayout.setBackgroundDrawable(getContext().getDrawable(bg_light));
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:

                break;
            default:
                settingslayout.setBackgroundColor(getContext().getColor(R.color.colorPrimarygray));
        }

        brigthnesslider.setValue(getBrightness());

        brigthnesslider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                //Use the value
                WindowManager.LayoutParams layout = getActivity().getWindow().getAttributes();
                layout.screenBrightness = value;
                getActivity().getWindow().setAttributes(layout);
            }
        });


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

        callswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    callswitch.setChecked(true);
                    callswitch.setTrackTintList(ColorStateList.valueOf(Color.GREEN));
                }
                else{
                    callswitch.setChecked(false);
                    callswitch.setTrackTintList(ColorStateList.valueOf(Color.WHITE));
                }
            }
        });

        musicswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    musicswitch.setChecked(true);
                    musicswitch.setTrackTintList(ColorStateList.valueOf(Color.GREEN));
                }
                else{
                    musicswitch.setChecked(false);
                    musicswitch.setTrackTintList(ColorStateList.valueOf(Color.WHITE));
                }
            }
        });

        darkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
                    case Configuration.UI_MODE_NIGHT_YES:
                        //process
                        break;
                    case Configuration.UI_MODE_NIGHT_NO:
                        // process
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                        break;
                }

            }
        });

        lightbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
                    case Configuration.UI_MODE_NIGHT_YES:
                        //process
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case Configuration.UI_MODE_NIGHT_NO:
                        // process
                        break;
                }

            }
        });

    }


  public float getBrightness() {
      float curBrightnessValue = 0;
      try {
          ContentResolver ContentResolver = new ContentResolver(getContext()) {
          };
          curBrightnessValue = android.provider.Settings.System.getInt(
                  ContentResolver, android.provider.Settings.System.SCREEN_BRIGHTNESS);
      } catch (Settings.SettingNotFoundException e) {
          e.printStackTrace();
      }

      if(curBrightnessValue > 100){
          curBrightnessValue = 100.0F;
      }

      return curBrightnessValue;
  }
}