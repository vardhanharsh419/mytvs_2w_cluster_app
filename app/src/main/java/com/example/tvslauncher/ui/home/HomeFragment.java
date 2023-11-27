package com.example.tvslauncher.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tvslauncher.ArcDrawable;
import com.example.tvslauncher.R;
import com.example.tvslauncher.SemiCircleProgressBarView;
import com.example.tvslauncher.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    int numberOfTimesToBlink = 4000;
    long blinkInterval = 500;  // 1 second
    private FragmentHomeBinding binding;
    private SemiCircleProgressBarView semiCircleProgressBarView;
    private ArcDrawable arc = new ArcDrawable();
    private int test = 0;

    ImageView fuel_indicator, previous_btn, pause_btn, next_btn, tripicon, fuel_icon;

    @SuppressLint("ResourceAsColor")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ImageView leftindicator = binding.leftindicator;
        previous_btn = binding.previousBtn;
        pause_btn = binding.pauseBtn;
        next_btn = binding.nextBtn;
        fuel_indicator = binding.fuelIndicator;
        tripicon = binding.tripicon;
        fuel_icon = binding.fuelIcon;

//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        final ImageView fuel_bar = binding.fuelBar;
        fuel_bar.setBackgroundDrawable(arc);

        checkmode();

//        blinkingImageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.yourFirstImage));
//        blinkingImageView.setTag("yourFirstImage");
//
//        final CountDownTimer blinkTimer = new CountDownTimer((numberOfTimesToBlink+1)*1000, blinkInterval) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                if (blinkingImageView.getTag() == "yourFirstImage") {
//                    blinkingImageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.yourSecondImage));
//                    blinkingImageView.setTag("yourSecondImage");
//                }
//                else if (blinkingImageView.getTag() == "yourSecondImage") {
//                    blinkingImageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.yourFirstImage));
//                    blinkingImageView.setTag("yourFirstImage");
//                }
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        };

        leftindicator.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.indicator_left_inactive));
        leftindicator.setTag("yourFirstImage");

        final CountDownTimer blinkTimer = new CountDownTimer((numberOfTimesToBlink+1)*1000, blinkInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (leftindicator.getTag() == "yourFirstImage") {
                    leftindicator.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.indicator_left_active));
                    leftindicator.setTag("yourSecondImage");

                }
                else if (leftindicator.getTag() == "yourSecondImage") {
                    leftindicator.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.indicator_left_inactive));
                    leftindicator.setTag("yourFirstImage");
                }
            }

            @Override
            public void onFinish() {

            }
        };

        leftindicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(test == 0){
                    test = 1;
                    blinkTimer.start();
                }

                else{
                    test = 0;
                    blinkTimer.cancel();
                }

            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void checkmode(){
        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:

                pause_btn.setImageResource(R.drawable.pause_icon_light);
                previous_btn.setImageResource(R.drawable.previous_icon_light);
                next_btn.setImageResource(R.drawable.next_icon_light);
                tripicon.setImageResource(R.drawable.trip_dash_icon_dark);
                fuel_indicator.setImageResource(R.drawable.fuel_indicator_light);
                fuel_icon.setImageResource(R.drawable.fuel_icon_white);


                break;

            case Configuration.UI_MODE_NIGHT_NO:
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                break;
        }
    }

}