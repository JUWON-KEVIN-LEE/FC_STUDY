package com.immymemine.kevin.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewPager();
        setTabLayout();
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
    private void setTabLayout() {
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("One"));
        tabLayout.addTab(tabLayout.newTab().setText("Two"));
        tabLayout.addTab(tabLayout.newTab().setText("Three"));
        tabLayout.addTab(tabLayout.newTab().setText("Four"));
//        tabLayout.addTab(tabLayout.newTab().setText("Five"));
//        tabLayout.addTab(tabLayout.newTab().setText("Six"));
//        tabLayout.addTab(tabLayout.newTab().setText("Seven"));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }
    private void setViewPager() {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
//        List<Fragment> data = new ArrayList<>();
//        data.add(new Fragment_one());
//        data.add(new Fragment_two());
//        data.add(new Fragment_three());
//        data.add(new Fragment_four());
//        data.add(new Fragment_five());
//        data.add(new Fragment_six());
//        data.add(new Fragment_seven());
        CustomAdapter adapter = new CustomAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
