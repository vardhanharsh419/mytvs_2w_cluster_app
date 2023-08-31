package com.example.tvslauncher.ui.home;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tvslauncher.R;
import com.example.tvslauncher.SemiCircleProgressBarView;
import com.example.tvslauncher.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    int numberOfTimesToBlink = 4000;
    long blinkInterval = 500;  // 1 second
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ImageView leftindicator = binding.leftindicator;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

//
//        try {
//            SemiCircleProgressBarView semiCircleProgressBarView = (SemiCircleProgressBarView) root.findViewById(R.id.progress);
//            semiCircleProgressBarView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//
//            semiCircleProgressBarView.setClipping(60);
//        }catch (Exception e){
//            System.out.println(e);
//        }


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

//        leftindicator.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.indicator_left_inactive));
//        leftindicator.setTag("yourFirstImage");
//
//        final CountDownTimer blinkTimer = new CountDownTimer((numberOfTimesToBlink+1)*1000, blinkInterval) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                if (leftindicator.getTag() == "yourFirstImage") {
//                    leftindicator.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.indicator_left_active));
//                    leftindicator.setTag("yourSecondImage");
//
//                }
//                else if (leftindicator.getTag() == "yourSecondImage") {
//                    leftindicator.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.indicator_left_inactive));
//                    leftindicator.setTag("yourFirstImage");
//                }
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        };
//
//        blinkTimer.start();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}