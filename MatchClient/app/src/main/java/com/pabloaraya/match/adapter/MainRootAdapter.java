package com.pabloaraya.match.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pabloaraya.match.R;
import com.pabloaraya.match.fragment.PrivateMessageFragment;
import com.pabloaraya.match.fragment.ProfileFragment;
import com.pabloaraya.match.fragment.BlindlyFragment;

/**
 * Created by pablo on 3/10/15.
 */
public class MainRootAdapter extends FragmentPagerAdapter {

    Context context;

    private int[] imageResId = {
            R.drawable.ic_action_people,
            R.drawable.ic_action_people,
            R.drawable.ic_action_profile
    };

    public MainRootAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.

        switch(position) {
            case 0:
                return BlindlyFragment.newInstance();
            case 1:
                return PrivateMessageFragment.newInstance();
            case 2:
                return ProfileFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Nearby People";
            case 1:
                return "Private Messages";
            case 2:
                return "Profile";
        }
        return null;
    }

    public int getIcon(int i){
        return imageResId[i];
    }
}
