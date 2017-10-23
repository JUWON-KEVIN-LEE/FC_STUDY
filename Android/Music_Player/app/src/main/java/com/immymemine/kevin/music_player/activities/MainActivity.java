package com.immymemine.kevin.music_player.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.immymemine.kevin.music_player.R;
import com.immymemine.kevin.music_player.adapter.ViewPagerAdapter;
import com.immymemine.kevin.music_player.custom_view.CircleImageView;
import com.immymemine.kevin.music_player.fragments.AlbumFragment;
import com.immymemine.kevin.music_player.fragments.ArtistFragment;
import com.immymemine.kevin.music_player.fragments.GenreFragment;
import com.immymemine.kevin.music_player.fragments.InteractionListener;
import com.immymemine.kevin.music_player.fragments.PlayerFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends PlayerActivity implements InteractionListener {
    TextView titleView, artistView;
    ViewPager viewPager;
    TabLayout tabLayout;
    CircleImageView civ_album;
    ImageButton ibPre, ibStart, ibNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewPager();
        initTabLayout();
    }

    @Override
    protected void onResume() {
        setNaviBar();
        if(getIsPlaying())
            ibStart.setImageResource(R.drawable.ic_pause);
        else
            ibStart.setImageResource(R.drawable.ic_play);
        super.onResume();
    }

    // init view methods
    private void initView() {
        titleView = (TextView) findViewById(R.id.titleView);
        artistView = (TextView) findViewById(R.id.artistView);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        // circle image view
        civ_album = (CircleImageView) findViewById(R.id.civ_album);
        civ_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                startActivity(intent);
            }
        });

        // button
        ibPre = (ImageButton) findViewById(R.id.ib_pre);
        ibStart = (ImageButton) findViewById(R.id.ib_start);
        if(getIsPlaying()) {
            ibStart.setImageResource(R.drawable.ic_pause);
        }
        ibNext = (ImageButton) findViewById(R.id.ib_next);
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_element1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_element2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_element3));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_element4));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private void initViewPager() {
        List<Fragment> list = new ArrayList<>();
        PlayerFragment p = PlayerFragment.getInstance();
        AlbumFragment a = AlbumFragment.getInstance();
        ArtistFragment ar = ArtistFragment.getInstance();
        GenreFragment g = GenreFragment.getInstance();
        list.add(p);    list.add(a);    list.add(ar);   list.add(g);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void play(View view) {
        if(getIsPlaying()) {
            pause(view);
            return;
        }
        super.play(view);
        ibStart.setImageResource(R.drawable.ic_pause);
    }

    @Override
    public void pause(View view) {
        super.pause(view);
        ibStart.setImageResource(R.drawable.ic_play);
    }

    @Override
    public void next(View view) {
        super.next(view);
        setNaviBar();
    }

    @Override
    public void pre(View view) {
        super.pre(view);
        setNaviBar();
    }

    @Override
    public void playByList(int position) {
        super.playByList(position);
        ibStart.setImageResource(R.drawable.ic_pause);
        setNaviBar();
    }

    public void setNaviBar() {
        titleView.setText(data.get(getmCurrentPosition()).getTitle());
        artistView.setText(data.get(getmCurrentPosition()).getArtist());
        Glide.with(this).load(data.get(getmCurrentPosition()).getAlbumUri()).into(civ_album);
    }
}
