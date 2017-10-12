package com.immymemine.kevin.musicplayer.player;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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

public class PlayerActivity extends AppCompatActivity implements SeekBarThread.IObserver{
    TextView tvTitle, tvArtist;
    ViewPager viewPager;
    ImageView ivList;
    ImageButton ibPre, ibBack, ibStart, ibForward, ibNext;
    SeekBar seekBar;
    List<SongContent.SongItem> data;
    // data 에 접근해서 view pager 작동시켜야 함
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        data = SongContent.ITEMS;
        getIntentValue();
        connectService();
        initView();
        initViewPager();
        initListener();
        thread = SeekBarThread.getInstance();
//        thread.add(this);
    }

    // 선택된 아이템 포지션값
    int currentPosition;
    Intent intent;
    private void getIntentValue() {
        intent = getIntent();
        currentPosition = intent.getIntExtra(Const.KEY_POSITION, -1);
        now = intent.getIntExtra(Const.STAT, -1);
    }

    // 서비스 연결
    Intent serviceIntent;
    private void connectService() {
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
    int duration;
    int currentTime;
    ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isService = true;
            musicPlayer = ((MusicPlayer.CustomBinder)service).getMusicPlayer();
            duration = musicPlayer.getDuration();
            currentTime = musicPlayer.getCurrentPosition();
            setSeekBar();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isService = false;
        }
    };
    SeekBarThread thread = null;
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

    int now;
    private void start() {
        now = Const.STAT_ISPLAYING;
        ibStart.setImageResource(android.R.drawable.ic_media_pause);
        musicPlayer.play(currentPosition);
    }

    @Override
    protected void onResume() {
            thread.add(this);
        super.onResume();
    }

    private void pause() {
        now = Const.STAT_ISSTOP;
        ibStart.setImageResource(android.R.drawable.ic_media_play);
        musicPlayer.pause();
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvArtist = (TextView) findViewById(R.id.tv_artist);
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
        PlayerViewPagerAdapter adapter = new PlayerViewPagerAdapter(this, data);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
//                if(now == Const.STAT_ISPLAYING) {
//                    start();
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if(currentPosition > -1)
            viewPager.setCurrentItem(currentPosition);
    }

    @Override
    public void finish() {
        thread.remove(this);
        thread.setStop();
        intent = getIntent();
        intent.putExtra(Const.KEY_POSITION, currentPosition);
        intent.putExtra(Const.STAT, now);
        setResult(RESULT_OK, intent);
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
                    currentPosition = data.size()-1;
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
                if(currentPosition == data.size()-1)
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
                while(event.getAction() == MotionEvent.ACTION_DOWN) {
                    fastMove = new Thread() {
                        public void run() {
                            musicPlayer.back();
                        }
                    };
                    fastMove.start();
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
                            musicPlayer.forward();
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
                    if(seekBar.getProgress() == musicPlayer.getDuration()) {
                        if(currentPosition == data.size()-1)
                            currentPosition = 0;
                        else
                            currentPosition += 1;
                        musicPlayer.move(currentPosition);
                        viewPager.setCurrentItem(currentPosition);
                    }
                    seekBar.setMax(musicPlayer.getDuration());
                    seekBar.setProgress(musicPlayer.getCurrentPosition());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
