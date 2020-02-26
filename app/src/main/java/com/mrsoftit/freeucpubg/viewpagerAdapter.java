package com.mrsoftit.freeucpubg;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class viewpagerAdapter extends FragmentStatePagerAdapter {

    private String fragmnet[] = {"FREE UC OFFER ","UC BUY "};

    public viewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                return new Fragment_offer();

            default:
                return new  Fragment_Uc_buy();

        }

    }

    @Override
    public int getCount() {

        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {

        }
        return null;
    }
}

