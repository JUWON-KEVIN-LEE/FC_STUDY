package com.immymemine.kevin.music_player.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.immymemine.kevin.music_player.R;
import com.immymemine.kevin.music_player.adapter.DetailViewPagerAdater;

public class Details extends Players {
    ViewPager viewPager;
    SeekBar playerSeekBar;
    ImageView ivList;
    ImageButton ibPre, ibBack, ibStart, ibForward, ibNext;
    int mCurrentPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initViewPager();
        setSeekBar();
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        DetailViewPagerAdater adapter = new DetailViewPagerAdater(this, data);
        viewPager.setAdapter(adapter);

        mCurrentPosition = 0;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(mCurrentPosition);
    }

    private void initView() {
        playerSeekBar = (SeekBar) findViewById(R.id.playerSeekBar);
        ivList = (ImageView) findViewById(R.id.iv_list);
        ivList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ibPre = (ImageButton) findViewById(R.id.ib_pre);
        ibBack = (ImageButton) findViewById(R.id.ib_back);
        ibStart = (ImageButton) findViewById(R.id.ib_start);

        if(super.getIsPlaying()) {
            runFlag = true;
            ibStart.setImageResource(android.R.drawable.ic_media_pause);
        } else
            runFlag = false;
        ibForward = (ImageButton) findViewById(R.id.ib_forward);
        ibNext = (ImageButton) findViewById(R.id.ib_next);
    }

    @Override
    public void play(View view) {
        if(getIsPlaying()) {
            pause(view);
            return;
        }
        super.playByList(mCurrentPosition);
        ibStart.setImageResource(android.R.drawable.ic_media_pause);
    }



    @Override
    public void pause(View view) {
        super.pause(view);
        ibStart.setImageResource(android.R.drawable.ic_media_play);
    }

    @Override
    public void next(View view) {
        super.next(view);
    }

    @Override
    public void pre(View view) {
        super.pre(view);
    }

    public boolean runFlag;
    private void setSeekBar() {
        new Thread() {
            public void run() {
                while(runFlag) {
                    playerSeekBar.setProgress(getPosition());

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        playerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
