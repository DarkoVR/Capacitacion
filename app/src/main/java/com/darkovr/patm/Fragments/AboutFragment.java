package com.darkovr.patm.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darkovr.patm.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutFragment extends Fragment {

    @BindView(R.id.txt_developer) TextView nameDeveloper;
    @BindView(R.id.txt_tecno) TextView http_tecno;
    @BindView(R.id.txt_email) TextView emailDeveloper;
    @BindView(R.id.txt_phone) TextView phoneDeveloper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_about, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Linkify.addLinks(http_tecno, Linkify.WEB_URLS);
        Linkify.addLinks(emailDeveloper, Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks(phoneDeveloper, Linkify.PHONE_NUMBERS);

    }
}