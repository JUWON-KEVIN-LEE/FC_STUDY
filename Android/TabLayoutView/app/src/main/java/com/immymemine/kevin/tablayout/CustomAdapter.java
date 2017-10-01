package com.immymemine.kevin.tablayout;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Pager Adapter < View Array => 동작
 */

public class CustomAdapter extends PagerAdapter {
    private static final int COUNT = 4;
    List<View> views;
    public CustomAdapter(Context context) {
        super();
        views = new ArrayList<>();
        views.add(new One(context));
        views.add(new Two(context));
        views.add(new Three(context));
        views.add(new Four(context));
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
        //super.destroyItem(container, position, object);
    }
    @Override
    public int getCount() {
        return COUNT;
    }
}
