package com.pabloaraya.match.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pabloaraya.match.R;

public class PrivateMessageFragment extends Fragment {

    public static PrivateMessageFragment newInstance() {
        PrivateMessageFragment fragment = new PrivateMessageFragment();
        return fragment;
    }

    public PrivateMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_private_message, container, false);
    }
}