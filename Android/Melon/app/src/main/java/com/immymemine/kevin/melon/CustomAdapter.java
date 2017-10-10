package com.immymemine.kevin.melon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.immymemine.kevin.melon.viewpager_fragment.consert;
import com.immymemine.kevin.melon.viewpager_fragment.feed;
import com.immymemine.kevin.melon.viewpager_fragment.for_u;
import com.immymemine.kevin.melon.viewpager_fragment.music;
import com.immymemine.kevin.melon.viewpager_fragment.my;
import com.immymemine.kevin.melon.viewpager_fragment.star;
import com.immymemine.kevin.melon.viewpager_fragment.video;

/**
 * Created by quf93 on 2017-09-27.
 */

public class CustomAdapter extends FragmentStatePagerAdapter {
    public CustomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new video();
            case 2:
                return new my();
            case 3:
                return new for_u();
            case 4:
                return new star();
            case 5:
                return new feed();
            case 6:
                return new consert();
            default:
                return new music();
        }
    }
    private static final int COUNT = 7;
    @Override
    public int getCount() {
        return COUNT;
    }
}
