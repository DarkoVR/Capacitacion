package com.darkovr.patm.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.darkovr.patm.AsyncTasks.SplashThread;
import com.darkovr.patm.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenFragment extends Fragment {

    @BindView(R.id.splashProgressBar) ProgressBar splashBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_splash, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SplashThread thread = new SplashThread(splashBar,getContext());
        thread.execute();
    }
}