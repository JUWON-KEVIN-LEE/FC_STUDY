package com.immymemine.kevin.musicplayer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.immymemine.kevin.musicplayer.model.SongContent;
import com.immymemine.kevin.musicplayer.player.MusicPlayer;
import com.immymemine.kevin.musicplayer.player.PlayerActivity;
import com.immymemine.kevin.musicplayer.utilities.BaseActivity;
import com.immymemine.kevin.musicplayer.utilities.CircleImageView;
import com.immymemine.kevin.musicplayer.utilities.Const;
import com.immymemine.kevin.musicplayer.view_pagers.MusicFragment;
import com.immymemine.kevin.musicplayer.view_pagers.ViewPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MusicFragment.OnListFragmentInteractionListener{
    ViewPager viewPager;
    TabLayout tabLayout;
    SongContent songContent;
    CircleImageView civ_album;
    ImageButton ibPre, ibStart, ibNext;
    TextView tv_time, tv_duration;
    int currentPosition, limitPosition;

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        // external storage 에서 date 가져와서 static ITEMS 에 담아주기
        songContent = new SongContent();
        songContent.setList(this);
        limitPosition = songContent.ITEMS.size();
        // ui
        initView();
        initTabLayout();
        initViewPager();
        //
        initPlayer();
        initListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Const.REQUEST_CODE) {
            currentPosition = data.getIntExtra(Const.KEY_POSITION, -1);
            now = data.getIntExtra(Const.STAT, -1);
            refreshPlayButton();
            setDuration();
        }
    }

    private void setDuration() {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        String duration = sdf.format(musicPlayer.getDuration());
        tv_duration.setText(duration);
    }

    private void refreshPlayButton() {
        if(now == Const.STAT_ISPLAYING)
            ibStart.setImageResource(android.R.drawable.ic_media_pause);
        else
            ibStart.setImageResource(android.R.drawable.ic_media_play);
    }

    private void initView() {
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_duration = (TextView) findViewById(R.id.durationView);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        civ_album = (CircleImageView) findViewById(R.id.civ_album);
        ibPre = (ImageButton) findViewById(R.id.ib_pre);
        ibStart = (ImageButton) findViewById(R.id.ib_start);
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
        // Adapter 에 data..
        // viewPager.setAdapter();
        List<Fragment> list = new ArrayList<>();
        MusicFragment music = MusicFragment.newInstance(1);
        MusicFragment album = MusicFragment.newInstance(1);
        MusicFragment artist = MusicFragment.newInstance(1);
        MusicFragment genre = MusicFragment.newInstance(1);
        list.add(music); list.add(album); list.add(artist); list.add(genre);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void playMusicPlayer(int position) {
        currentPosition = position;
        playMP(position);
        setDuration();
    }

    // 앨범 아트 클릭시 이동
    Intent intent = null;
    private void moveToPlayerActivity() {
        if(intent == null)
            intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(Const.KEY_POSITION, currentPosition);
        intent.putExtra(Const.STAT, now);
        startActivityForResult(intent, Const.REQUEST_CODE);
    }

    // player 와 연결
    Intent serviceIntent;
    private void initPlayer() {
        currentPosition = 0;
        // civ_album.setImageURI(data.get(Const.DEFAULT_POSITION).albumUri);
        serviceIntent = new Intent(this, MusicPlayer.class);
        serviceIntent.putExtra(Const.KEY_POSITION, currentPosition);
        startService(serviceIntent);
        bindService();
    }
    private void bindService() {
        bindService(serviceIntent, con, Context.BIND_AUTO_CREATE);
    }
    private void unBindService() {
        unbindService(con);
    }
    boolean isService = false;
    MusicPlayer musicPlayer;
    ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isService = true;
            musicPlayer = ((MusicPlayer.CustomBinder)service).getMusicPlayer();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isService = false;
        }
    };

    private void playMP() {
        if(now == Const.STAT_ISSTOP) {
            now = Const.STAT_ISPLAYING;
            musicPlayer.play(currentPosition);
            refreshPlayButton();
        } else {
            now = Const.STAT_ISSTOP;
            musicPlayer.pause();
            refreshPlayButton();
        }
    }
    private void playMP(int position) {
        if(now == Const.STAT_ISPLAYING)
            musicPlayer.move(position);
        else {
            now = Const.STAT_ISPLAYING;
            musicPlayer.play(position);
            refreshPlayButton();
        }
    }

    int now = Const.STAT_ISSTOP;
    private void initListener() {
        // 재생 버튼 클릭
        ibStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMP();
                setDuration();
            }
        });

        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition == limitPosition-1)
                    currentPosition = 0;
                else
                    currentPosition += 1;
//                if(now == Const.STAT_ISPLAYING) {
                musicPlayer.move(currentPosition);
                setDuration();
//                }
            }
        });

        ibPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition == 0)
                    currentPosition = limitPosition-1;
                else
                    currentPosition -= 1;
//                if(now == Const.STAT_ISPLAYING) {
                musicPlayer.move(currentPosition);
                setDuration();
//                }
            }
        });

        civ_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToPlayerActivity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        unBindService();
        super.onDestroy();
    }
}
