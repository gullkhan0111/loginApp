package com.example.haseeb.loginapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.haseeb.loginapp.fragment.FragmentAllUsers;
import com.example.haseeb.loginapp.fragment.FragmentCurrentUser;

public class PagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int number) {
        super(fm);
        this.mNumOfTabs = number;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentCurrentUser();
            case 1:
                return new FragmentAllUsers();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
