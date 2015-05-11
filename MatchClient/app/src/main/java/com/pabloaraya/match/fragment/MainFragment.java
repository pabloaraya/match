package com.pabloaraya.match.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pabloaraya.match.NonSwipeableViewPager;
import com.pabloaraya.match.R;
import com.pabloaraya.match.adapter.MainRootAdapter;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainFragment extends Fragment implements MaterialTabListener {

    /* Private vars */
    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private NonSwipeableViewPager mViewPager;
    private MainRootAdapter chatListAdapter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chatListAdapter = new MainRootAdapter(getActivity(), getChildFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        /* Set actionbar */
        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((ActionBarActivity)getActivity()).setSupportActionBar(toolbar);
            ((ActionBarActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        tabHost = (MaterialTabHost)view.findViewById(R.id.materialTabHost);

        mViewPager = (NonSwipeableViewPager) view.findViewById(R.id.pagerChat);
        mViewPager.setAdapter(chatListAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < chatListAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setIcon(getResources().getDrawable(chatListAdapter.getIcon(i)))
                            .setTabListener(this)
            );
        }
        return view;
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        mViewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }
}
