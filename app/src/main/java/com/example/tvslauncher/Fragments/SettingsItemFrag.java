
package com.example.tvslauncher.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvslauncher.R;

public class SettingsItemFrag extends Fragment {

    public SettingsItemFrag() {}

    public static SettingsItemFrag newInstance(String param1, String param2) {
        SettingsItemFrag fragment = new SettingsItemFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings_item, container, false);
    }
}