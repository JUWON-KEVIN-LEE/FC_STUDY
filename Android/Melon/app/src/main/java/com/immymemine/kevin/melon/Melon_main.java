package com.immymemine.kevin.melon;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class Melon_main extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melon_main);
        setViewPager();
        setTabLayout();
        setListener();
    }

    private void setListener() {
        // TabLayout 과 ViewPager 연결
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        // ViewPager 변경 사항을 TabLayout 에 전달
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setViewPager() {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapter = new CustomAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private void setTabLayout() {
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("뮤직"));
        tabLayout.addTab(tabLayout.newTab().setText("비디오"));
        tabLayout.addTab(tabLayout.newTab().setText("MY"));
        tabLayout.addTab(tabLayout.newTab().setText("For U"));
        tabLayout.addTab(tabLayout.newTab().setText("스타"));
        tabLayout.addTab(tabLayout.newTab().setText("피드"));
        tabLayout.addTab(tabLayout.newTab().setText("콘서트"));

    }
}
