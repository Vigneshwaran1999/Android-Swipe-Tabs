package com.SODD.jswipetabs.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SODD.jswipetabs.app.Main;
import com.SODD.jswipetabs.app.R;

public class FragmentOne extends Fragment {

    View rootView;
    Main activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_one, container, false);
        activity = (Main) getActivity();
        return rootView;
    }
}
