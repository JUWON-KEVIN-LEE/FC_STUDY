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
import com.immymemine.kevin.musicplayer.player.SeekBarThread;
import com.immymemine.kevin.musicplayer.utilities.BaseActivity;
import com.immymemine.kevin.musicplayer.utilities.CircleImageView;
import com.immymemine.kevin.musicplayer.utilities.Const;
import com.immymemine.kevin.musicplayer.view_pagers.MusicFragment;
import com.immymemine.kevin.musicplayer.view_pagers.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MusicFragment.OnListFragmentInteractionListener, SeekBarThread.IObserver {
    ViewPager viewPager;
    TabLayout tabLayout;
    CircleImageView civ_album;
    ImageButton ibPre, ibStart, ibNext;
    TextView tv_time, tv_duration;

    int now = Const.STAT_ISSTOP;
    int currentPosition, limitPosition;
    MusicPlayer musicPlayer;
    SeekBarThread thread;

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        // ui
        initView();
        initTabLayout();
        initViewPager();
        // player setting
        initPlayer();
        initListener();
    }
    @Override
    protected void onStart() {
        // external storage 에서 date 가져와서 static ITEMS 에 담아주기
        initData();
        throwMeToThread();
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void finish() {
        thread.setStop();
        super.finish();
    }
    // resume 할 때 musicplayer 로 부터 상태를 받아와라!

    // init view methods
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

    // external storage 에서 songs 가져와서 data 에 setting < onStart()
    private void initData() {
        SongContent songContent = SongContent.getInstance();
        songContent.setList(this);
        limitPosition = SongContent.ITEMS.size();
    }
    private void throwMeToThread() {
        thread = SeekBarThread.getInstance();
        thread.add(this);
        thread.start();
    }

    // player 와 연결
    Intent serviceIntent;
    private void initPlayer() {
        currentPosition = 0;
        serviceIntent = new Intent(this, MusicPlayer.class);
        serviceIntent.putExtra(Const.KEY_POSITION, currentPosition); // 최초에 연결시 position 값을 0으로 전달
        startService(serviceIntent);
        bindService();
    }
    private void bindService() {
        bindService(serviceIntent, con, Context.BIND_AUTO_CREATE);
    }
    private void unBindService() {
        unbindService(con);
    }
    ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicPlayer = ((MusicPlayer.CustomBinder)service).getMusicPlayer();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) { }
    };

    // action 에 따른 view refresh methods
    private void togglePlayButton() {
        if(now == Const.STAT_ISPLAYING)
            ibStart.setImageResource(android.R.drawable.ic_media_pause);
        else
            ibStart.setImageResource(android.R.drawable.ic_media_play);
    }
    private void setDuration() {
        tv_duration.setText(musicPlayer.getDuration());
    }
    private void setAlbumArt() {
        // civ_album.setImageResource(.......);
    }

    // holder onClickListener 에 의한 method 실행
    @Override
    public void playMusicPlayer(int position) {
        currentPosition = position; // 클릭된 position 값 (+/실행되고 있는 position 값)을 main 에서도 가지고 있도록
        now = Const.STAT_ISPLAYING;
        sendCommandToService(Const.PLAY, currentPosition);
    }
    private void sendCommandToService(String cmd, int currentPosition) {
        musicPlayer.getCommand(cmd, currentPosition);
        // refresh view
        togglePlayButton();
        setDuration();
        // setAlbumArt();
    }

    private void initListener() {
        // 재생 버튼 클릭
        ibStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(now == Const.STAT_ISPLAYING) {
                    now = Const.STAT_ISSTOP;
                    sendCommandToService(Const.PAUSE, currentPosition);
                } else {
                    now = Const.STAT_ISPLAYING;
                    sendCommandToService(Const.PLAY, currentPosition);
                }
                // pause 상태였는지 아니면 아예 stop 이었는지 판단해서..?
            }
        });

        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition == limitPosition-1)
                    currentPosition = 0;
                else
                    currentPosition += 1;
                if( "00:00".equals(tv_time.getText().toString()) && now == Const.STAT_ISPLAYING)
                    sendCommandToService(Const.NEXT, currentPosition);
                else {
                    now = Const.STAT_ISPLAYING;
                    sendCommandToService(Const.NEXT, currentPosition);
                }
                setDuration();
            }
        });

        ibPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition == 0)
                    currentPosition = limitPosition-1;
                else
                    currentPosition -= 1;

                if( "00:00".equals(tv_time.getText().toString()) && now == Const.STAT_ISPLAYING)
                    sendCommandToService(Const.PRE, currentPosition);
                else {
                    now = Const.STAT_ISPLAYING;
                    sendCommandToService(Const.PRE, currentPosition);
                }
                setDuration();
            }
        });

        civ_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToPlayerActivity();
            }
        });
    }

    // 앨범 아트 클릭시 이동
    Intent intent = null;
    private void moveToPlayerActivity() {
        if(intent == null)
            intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        unBindService();
        super.onDestroy();
    }
    private void setCurrentTime() {
        tv_time.setText(musicPlayer.getCurrentTime());
        if(tv_time.getText().equals(tv_duration.getText())) {
            if(currentPosition == limitPosition-1)
                currentPosition = 0;
            else
                currentPosition += 1;
            if( "00:00".equals(tv_time.getText().toString()) && now == Const.STAT_ISPLAYING)
                sendCommandToService(Const.NEXT, currentPosition);
            else {
                now = Const.STAT_ISPLAYING;
                sendCommandToService(Const.NEXT, currentPosition);
            }
            setDuration();
        }
    }
    @Override
    public void setProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(now == Const.STAT_ISPLAYING) {
                    setCurrentTime();

                }
            }
        });
    }
}
