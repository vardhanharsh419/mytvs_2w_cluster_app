package com.example.tvslauncher.Fragments;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tvslauncher.MainActivity;
import com.example.tvslauncher.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeftNav#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftNav extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private boolean homeclicked = false ;
    private boolean bluetoothclicked = false ;
    private boolean mediaclicked = false ;
    private ImageView home, bluetooth, media;
    private TextView hometxt, bluetoothtxt, mediatxt;
    private LinearLayout navlayout, musiclayout, homelayout, bluetoothlayout;
    private int modecheck = 0;
    public LeftNav() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeftNav.
     */
    // TODO: Rename and change types and number of parameters
    public static LeftNav newInstance(String param1, String param2) {
        LeftNav fragment = new LeftNav();
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
        return inflater.inflate(R.layout.fragment_left_nav, container, false);
    }

    @SuppressLint("ResourceAsColor")
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        FragmentManager fm = getFragmentManager();
        RightNav fragm = (RightNav) fm.findFragmentByTag("Frag_Right_tag");

        home = view.findViewById(R.id.homebtn);
        bluetooth = view.findViewById(R.id.ble);
        media = view.findViewById(R.id.musicbtn);

        hometxt = view.findViewById(R.id.hometxt);
        bluetoothtxt = view.findViewById(R.id.bletxt);
        mediatxt = view.findViewById(R.id.musictxt);
        navlayout = view.findViewById(R.id.navlayout);
        musiclayout = view.findViewById(R.id.musiclayout);
        homelayout = view.findViewById(R.id.homelayout);
        bluetoothlayout = view.findViewById(R.id.bluetoothlayout);

        hometxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
        home.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_active), PorterDuff.Mode.SRC_IN);

        homelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(homeclicked == false){
                    homeclicked = true;
                    hometxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
                    home.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_active), PorterDuff.Mode.SRC_IN);

                    bluetoothtxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    bluetooth.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

                    mediatxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    media.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

                }else{
                    homeclicked = false;
//                    hometxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
//                    home.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                }
                fragm.resetalltint();

                ((MainActivity)getActivity()).replacefrag(1, "Frag_Home_tag");

            }
        });

        bluetoothlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bluetoothclicked == false){
                    bluetoothclicked = true;
                    bluetoothtxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
                    bluetooth.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_active), PorterDuff.Mode.SRC_IN);

                    hometxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    home.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

                    mediatxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    media.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);


                }else{
                    bluetoothclicked = false;
//                    bluetoothtxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
//                    bluetooth.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                }
                fragm.resetalltint();

                ((MainActivity)getActivity()).replacefrag(2, "Bluetooth_Frag");

            }
        });

        musiclayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaclicked == false){
                    mediaclicked = true;
                    mediatxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
                    media.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_active), PorterDuff.Mode.SRC_IN);

                    hometxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    home.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

                    bluetoothtxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    bluetooth.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

                }else{
                    mediaclicked = false;
//                    mediatxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
//                    media.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                }
                fragm.resetalltint();
                ((MainActivity)getActivity()).replacefrag(3, "homefragment");

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
                navlayout.setBackground(getResources().getDrawable(R.drawable.left_side_nav_bar_dark));

                modecheck = 1;

                break;

            case Configuration.UI_MODE_NIGHT_NO:
                navlayout.setBackground(getResources().getDrawable(R.drawable.left_nav_light_bg));

                modecheck = 0;
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:

                break;
            default:
                navlayout.setBackgroundColor(getContext().getColor(R.color.colorPrimarygray));
                modecheck = 0;

        }
    }

    public void resetalltint() {
        hometxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
        home.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

        bluetoothtxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
        bluetooth.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

        mediatxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
        media.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

    }
}