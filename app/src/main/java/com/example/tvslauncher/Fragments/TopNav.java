package com.example.tvslauncher.Fragments;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tvslauncher.MainActivity;
import com.example.tvslauncher.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopNav#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopNav extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayout toplayout;

    public TopNav() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopNav.
     */
    // TODO: Rename and change types and number of parameters
    public static TopNav newInstance(String param1, String param2) {
        TopNav fragment = new TopNav();
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
        return inflater.inflate(R.layout.fragment_top_nav, container, false);
    }

    @SuppressLint("ResourceAsColor")
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        toplayout = view.findViewById(R.id.toplayout);
//        navigation = view.findViewById(R.id.navigation);
//        alerts = view.findViewById(R.id.alerts);
//        trips = view.findViewById(R.id.trips);
//
//        navtxt = view.findViewById(R.id.navigationtxt);
//        alerttxt = view.findViewById(R.id.alertstxt);
//        tripstxt = view.findViewById(R.id.tripstxt);
//        rightlayout = view.findViewById(R.id.rightlayout);
//
//        navigation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(navclicked == false){
//                    navclicked = true;
//                    navtxt.setTextColor(R.color.home_icon_color_active);
//                    navigationimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_active), PorterDuff.Mode.SRC_IN);
//
//                }else{
//                    navclicked = false;
//                    navtxt.setTextColor(R.color.home_icon_color_inactive);
//                    navigationimg.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
//                }
//
//                ((MainActivity)getActivity()).replacefrag(4);
//
//            }
//        });
//
//        alerts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(alertclicked == false){
//                    alertclicked = true;
//                    alerttxt.setTextColor(R.color.home_icon_color_active);
////                    alertsim.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_active), PorterDuff.Mode.SRC_IN);
//
//                }else{
//                    alertclicked = false;
//                    alerttxt.setTextColor(R.color.home_icon_color_inactive);
////                    alerts.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
//                }
//                ((MainActivity)getActivity()).replacefrag(5);
//
//            }
//        });
//
//        trips.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(tripsclicked == false){
//                    tripsclicked = true;
//                    tripstxt.setTextColor(R.color.home_icon_color_active);
////                    trips.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_active), PorterDuff.Mode.SRC_IN);
//
//                }else{
//                    tripsclicked = false;
//                    tripstxt.setTextColor(R.color.home_icon_color_inactive);
////                    trips.setColorFilter(getContext().getResources().getColor(R.color.home_icon_color_inactive), PorterDuff.Mode.SRC_IN);
//                }
//                ((MainActivity)getActivity()).replacefrag(6);
//            }
//        });
//

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