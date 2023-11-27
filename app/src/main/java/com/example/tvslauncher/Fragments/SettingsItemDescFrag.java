package com.example.tvslauncher.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvslauncher.R;

public class SettingsItemDescFrag extends Fragment {

    public SettingsItemDescFrag() {}
    public static SettingsItemDescFrag newInstance(String param1, String param2) {
        SettingsItemDescFrag fragment = new SettingsItemDescFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_item_desc, container, false);
    }
}