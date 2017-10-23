package com.immymemine.kevin.subway.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.immymemine.kevin.subway.fragment.Subway_Fragment;

import java.util.List;

/**
 * Created by quf93 on 2017-10-19.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter{
    List<Fragment> list;
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == list.size()) {
            return Subway_Fragment.newInstance("search");
        }
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size()+1;
    }
}
