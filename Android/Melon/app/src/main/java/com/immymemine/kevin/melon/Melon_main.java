package com.immymemine.kevin.melon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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

        init();
    }
    Intent musicPlayer;
    Button playBtn;
    private void init() {
        musicPlayer = new Intent(this, MusicPlayer.class);
        playBtn = (Button) findViewById(R.id.playBtn);
    }

    boolean isPlaying = false;

    public void startMusicService(View view) {
        if(isPlaying == false) {
            isPlaying = true;
            int musicId = R.raw.mr_chu;
            musicPlayer.putExtra("musicId", musicId);
            startService(musicPlayer);
            playBtn.setText("stop");
        } else {
            isPlaying = false;
            stopService(musicPlayer);
            playBtn.setText("play");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int clickedItemId = item.getItemId();
        switch (clickedItemId) {
            case R.id.menuSearch:
                // search 화면 띄우기
                break;
            case R.id.menuProfile:
                // 프로필 드로워블.. 뭐시기
                break;
        }
        return super.onOptionsItemSelected(item);
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
