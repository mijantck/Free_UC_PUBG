package com.mrsoftit.freeucpubg;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class viewpagerAdapter extends FragmentStatePagerAdapter {

    private String fragmnet[] = {"FREE UC OFFER ","UC BUY ","MY INFO"};

    public viewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                return new Fragment_offer();

            case 1:
                return new Fragment_Uc_buy();

            default:
                return new  Fragment_My_info();

        }

    }

    @Override
    public int getCount() {

        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {

        }
        return null;
    }
}

