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

public class RightNav extends Fragment {
    private boolean navclicked = false ;
    private boolean alertclicked = false ;
    private boolean tripsclicked = false ;
    private ImageView navigationimg, alertimg, tripimg;
    private LinearLayout navigation, alerts, trips;
    private TextView navtxt, alerttxt, tripstxt;
    private LinearLayout rightlayout;

    public RightNav() {
        // Required empty public constructor
    }

    public static RightNav newInstance(String param1, String param2) {
        RightNav fragment = new RightNav();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_right_nav, container, false);
    }


    @SuppressLint("ResourceAsColor")
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        FragmentManager fm = getFragmentManager();
        LeftNav fragm = (LeftNav) fm.findFragmentByTag("Frag_Left_tag");

        navigationimg = view.findViewById(R.id.navigationimg);
        navigation = view.findViewById(R.id.navlayout);
        alerts = view.findViewById(R.id.alertlayout);
        trips = view.findViewById(R.id.triplayout);
        alertimg = view.findViewById(R.id.alertimg);
        tripimg = view.findViewById(R.id.tripimg);

        navtxt = view.findViewById(R.id.navigationtxt);
        alerttxt = view.findViewById(R.id.alertstxt);
        tripstxt = view.findViewById(R.id.tripstxt);
        rightlayout = view.findViewById(R.id.rightlayout);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(navclicked == false){
                    navclicked = true;
                    navtxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
                    navigationimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_active), PorterDuff.Mode.SRC_IN);

                    alerttxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    alertimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

                    tripstxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    tripimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                }else{
                    navclicked = false;
//                    navtxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
//                    navigationimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                }

                fragm.resetalltint();

                ((MainActivity)getActivity()).replacefrag(4, "homefragment");

            }
        });

        alerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alertclicked == false){
                    alertclicked = true;
                    alerttxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
                    alertimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_active), PorterDuff.Mode.SRC_IN);

                    navtxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    navigationimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

                    tripstxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    tripimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

                }else{
                    alertclicked = false;
//                    alerttxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
//                    alertimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                }
                fragm.resetalltint();
                ((MainActivity)getActivity()).replacefrag(5, "homefragment");

            }
        });

        trips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tripsclicked == false){
                    tripsclicked = true;
                    tripstxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
                    tripimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_active), PorterDuff.Mode.SRC_IN);

                    alerttxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    alertimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

                    navtxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
                    navigationimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                }else{
                    tripsclicked = false;
//                    tripstxt.setTextColor(getContext().getResources().getColor(R.color.home_icon_color_active));
//                    tripimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
                }
                fragm.resetalltint();

                ((MainActivity)getActivity()).replacefrag(6, "homefragment");
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
                rightlayout.setBackground(getResources().getDrawable(R.drawable.right_side_nav_dark));

                break;

            case Configuration.UI_MODE_NIGHT_NO:
                rightlayout.setBackground(getResources().getDrawable(R.drawable.right_nav_light_bg));

                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:

                break;
            default:
                rightlayout.setBackgroundColor(getContext().getColor(R.color.colorPrimarygray));
        }
    }

    public void resetalltint() {
        navtxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
        navigationimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

        alerttxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
        alertimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);

        tripstxt.setTextColor(getContext().getResources().getColor(R.color.textuniv));
        tripimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
    }
}