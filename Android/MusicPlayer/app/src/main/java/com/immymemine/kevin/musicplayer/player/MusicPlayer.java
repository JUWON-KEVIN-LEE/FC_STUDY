package com.immymemine.kevin.musicplayer.player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.immymemine.kevin.musicplayer.model.SongContent;
import com.immymemine.kevin.musicplayer.utilities.Const;
import com.immymemine.kevin.musicplayer.utilities.TimeViewUtil;

import java.util.List;

public class MusicPlayer extends Service {
    public MusicPlayer() {
    }

    public class CustomBinder extends Binder {
        public CustomBinder() {}
        public MusicPlayer getMusicPlayer() { return MusicPlayer.this; }
    }
    IBinder binder = new CustomBinder();
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("onBind()","==================================");
        return binder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    // data setting
    List<SongContent.SongItem> data;
    // timeview util
    TimeViewUtil timeutil;
    @Override
    public void onCreate() {
        Log.d("onCreate()","=================================== 1");
        data = SongContent.ITEMS;
        timeutil = TimeViewUtil.getInstance();
        super.onCreate();
    }

    int currentPosition;
    MediaPlayer mp;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("onStartCommand","=================================== 2");
        if(intent != null)
            if(mp == null) {
                currentPosition = intent.getIntExtra(Const.KEY_POSITION, -1);
                mp = MediaPlayer.create(this, data.get(currentPosition).musicUri);
                mp.setLooping(false);
            }
        return super.onStartCommand(intent, flags, startId);
    }

    public void getCommand(String cmd, int currentPosition) {
        switch (cmd) {
            case Const.PLAY:
                play(currentPosition);
                break;
            case Const.PAUSE:
                pause();
                break;
            case Const.NEXT:
                move(currentPosition);
                break;
            case Const.PRE:
                move(currentPosition);
                break;
            case Const.FAST_FORWARD:
                fastMove(Const.FAST_FORWARD);
                break;
            case Const.FAST_BACKWARD:
                fastMove(Const.FAST_BACKWARD);
                break;
        }
    }

    public void fastMove(String command) {
        if(command.equals(Const.FAST_FORWARD)) {
            if(currentTime >= duration)
                move(currentPosition + 1);
            else {
                currentTime += 5000;
                mp.seekTo(currentTime);
            }
        } else {
            if(currentTime <= 0) {
                if (currentPosition == 0) {
                    currentPosition = data.size() - 1;
                } else
                    currentPosition -= 1;

                move(currentPosition);
            } else {
                currentTime -= 5000;
                mp.seekTo(currentTime);
            }
        }
    }

    int now = Const.STAT_ISSTOP;
    // 플레이
    public void play(int position) {
        now = Const.STAT_ISPLAYING;
        if(currentPosition != position) {
            mp.release();
            currentPosition = position;
            mp = MediaPlayer.create(this, data.get(currentPosition).musicUri);
        }
        mp.start();
    }

    // 일시정지
    public void pause() {
        mp.pause();
        now = Const.STAT_ISSTOP;
    }

    // 이동
    public void move(int currentPosition) {
        this.currentPosition = currentPosition;
        if(now == Const.STAT_ISPLAYING || mp.getCurrentPosition() != 0) {
            mp.release();
            mp = MediaPlayer.create(this, data.get(currentPosition).musicUri);
            mp.setLooping(false);
            mp.start();
            now = Const.STAT_ISPLAYING;
        } else {
            mp = MediaPlayer.create(this, data.get(currentPosition).musicUri);
            mp.setLooping(false);
        }
    }

    // 지워도 될듯하다
    public boolean changeUI() {
        if(now == Const.STAT_ISPLAYING)
            return true;
        else
            return false;
    }
    int currentTime;
    // 현재 위치를 반환해주는 method 00:00 형태
    public String getCurrentTime() {
        currentTime = mp.getCurrentPosition();
        return timeutil.getCurrentTime(currentTime);
    }
    int duration;
    // duration 반환 00:00 형태로
    public String getDuration() {
        duration = Integer.parseInt(data.get(currentPosition).duration);
        return timeutil.getDuration(duration);
    }


    @Override
    public void onDestroy() {
        mp.release();
        super.onDestroy();
    }
}
