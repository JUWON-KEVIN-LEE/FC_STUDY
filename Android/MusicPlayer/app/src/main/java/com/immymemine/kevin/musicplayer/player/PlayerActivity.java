package com.immymemine.kevin.musicplayer.player;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.immymemine.kevin.musicplayer.R;
import com.immymemine.kevin.musicplayer.model.SongContent;
import com.immymemine.kevin.musicplayer.utilities.Const;

import java.util.List;

public class PlayerActivity extends AppCompatActivity implements SeekBarThread.IObserver {
    TextView tvTitle, tvArtist, tvCurrentTime, tvDuration;
    ViewPager viewPager;
    ImageView ivList;
    ImageButton ibPre, ibBack, ibStart, ibForward, ibNext;
    SeekBar seekBar;

    List<SongContent.SongItem> data;
    MusicPlayer musicPlayer;
    int currentPosition; // 선택된 아이템 포지션값
    int duration, currentTime;

    // data 에 접근해서 view pager 작동시켜야 함
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        // ui
        initView();
        initViewPager();
        initListener();
        // connect music player
        listService();
        connectService();
        Log.d("onCreate", "끝났어요==================");
    }

    void listService(){
        ActivityManager am = (ActivityManager)this.getSystemService(Activity.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(50);

        for (int i=0; i<rs.size(); i++) {
            ActivityManager.RunningServiceInfo rsi = rs.get(i);
            Log.i("Service", "Process " + rsi.process + " with component " + rsi.service.getClassName());
        }
    }

    SeekBarThread thread = null;
    @Override
    protected void onResume() {
        getMusicPlayerPlayInfo();
        setSeekBar();
        thread = SeekBarThread.getInstance();
        thread.add(this);
        super.onResume();
    }

    // view ui
    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvArtist = (TextView) findViewById(R.id.tv_artist);
        tvCurrentTime = (TextView) findViewById(R.id.tv_currentTime);
        tvDuration = (TextView) findViewById(R.id.tv_duration);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ivList = (ImageView) findViewById(R.id.iv_list);
        ibPre = (ImageButton) findViewById(R.id.ib_pre);
        ibBack = (ImageButton) findViewById(R.id.ib_back);
        ibStart = (ImageButton) findViewById(R.id.ib_start);
        ibForward = (ImageButton) findViewById(R.id.ib_forward);
        ibNext = (ImageButton) findViewById(R.id.ib_next);

        if(now == Const.STAT_ISPLAYING) {
            ibStart.setImageResource(android.R.drawable.ic_media_pause);
        }

        seekBar = (SeekBar) findViewById(R.id.playerSeekBar);
    }
    private void initViewPager() {
        PlayerViewPagerAdapter adapter = new PlayerViewPagerAdapter(this, SongContent.ITEMS);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if(currentPosition > -1)
            viewPager.setCurrentItem(currentPosition);
    }

    // 서비스 연결
    Intent serviceIntent;
    private void connectService() {
        serviceIntent = new Intent(this, MusicPlayer.class);
        //startService(serviceIntent);
        bindService();
    }
    private void bindService() {
        Log.d("bindService","==============================실행");
        bindService(serviceIntent, con, Context.BIND_AUTO_CREATE);
    }
    private void unBindService() {
        unbindService(con);
    }
    ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicPlayer = ((MusicPlayer.CustomBinder)service).getMusicPlayer();
            Log.d("onServiceConnetected","실행되었음===================");
//            duration = musicPlayer.getDuration();
//            currentTime = musicPlayer.getCurrentPosition();
//            setSeekBar();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) { }
    };

    String durationView, currentTimeView;
    int now, limitPosition;
    private void getMusicPlayerPlayInfo() {
        currentPosition = musicPlayer.currentPosition;
        durationView = musicPlayer.getDuration();
        currentTimeView = musicPlayer.getCurrentTime();
        duration = musicPlayer.duration;
        currentTime = musicPlayer.currentTime;
        now = musicPlayer.now;
        limitPosition = musicPlayer.data.size();
    }
    private void setSeekBar() {
        seekBar.setMax(duration);
        seekBar.setProgress(currentTime);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    musicPlayer.mp.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        thread.start();
    }

    private void start() {
        now = Const.STAT_ISPLAYING;
        ibStart.setImageResource(android.R.drawable.ic_media_pause);
        musicPlayer.getCommand(Const.PLAY, currentPosition);
    }
    private void pause() {
        now = Const.STAT_ISSTOP;
        ibStart.setImageResource(android.R.drawable.ic_media_play);
        musicPlayer.getCommand(Const.PAUSE, currentPosition);
    }

    @Override
    public void finish() {
        thread.setStop();
        thread.remove(this);
        super.finish();
    }
    @Override
    protected void onDestroy() {
        unBindService();
        super.onDestroy();
    }

    Thread fastMove;
    private void initListener() {
        ivList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ibStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(now == Const.STAT_ISPLAYING) {
                    pause();
                } else {
                    start();
                }
            }
        });

        ibPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition == 0)
                    currentPosition = limitPosition-1;
                else
                    currentPosition -= 1;
                musicPlayer.move(currentPosition);
                viewPager.setCurrentItem(currentPosition);
                if(musicPlayer.changeUI() == true)
                    ibStart.setImageResource(android.R.drawable.ic_media_pause);
                else
                    ibStart.setImageResource(android.R.drawable.ic_media_play);
            }
        });

        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition == limitPosition-1)
                    currentPosition = 0;
                else
                    currentPosition += 1;
                musicPlayer.move(currentPosition);
                if(musicPlayer.changeUI() == true) {
                    ibStart.setImageResource(android.R.drawable.ic_media_pause);
                }
                viewPager.setCurrentItem(currentPosition);
            }
        });

        ibBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(now == Const.STAT_ISPLAYING) {
                    while (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // currentTime 을 바꿔주면 된다!
                        musicPlayer.getCommand(Const.FAST_BACKWARD, currentPosition);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return false;
            }
        });

        ibForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                while(event.getAction() == MotionEvent.ACTION_DOWN) {
                    fastMove = new Thread() {
                        public void run() {
                            musicPlayer.getCommand(Const.FAST_FORWARD, currentPosition);
                        }
                    };
                    fastMove.start();
                }
                return false;
            }
        });
    }

    @Override
    public void setProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(now == Const.STAT_ISPLAYING) {
                    if(seekBar.getProgress() == musicPlayer.duration) {
                        if(currentPosition == limitPosition-1)
                            currentPosition = 0;
                        else
                            currentPosition += 1;
                        musicPlayer.getCommand(Const.NEXT, currentPosition);
                        viewPager.setCurrentItem(currentPosition);
                    }
                    tvCurrentTime.setText(musicPlayer.getCurrentTime());
                    seekBar.setMax(duration);
                    tvDuration.setText(musicPlayer.getDuration());
                    seekBar.setProgress(musicPlayer.currentTime);
                }
            }
        });
    }
}
