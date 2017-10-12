package com.immymemine.kevin.musicplayer.player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.immymemine.kevin.musicplayer.model.SongContent;
import com.immymemine.kevin.musicplayer.utilities.Const;

import java.util.List;

public class MusicPlayer extends Service {

    public MusicPlayer() {
    }

    public void forward() {
    }

    public void back() {
    }

    public class CustomBinder extends Binder {
        public CustomBinder() {}
        public MusicPlayer getMusicPlayer() { return MusicPlayer.this; }
    }

    IBinder binder = new CustomBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    // 데이터 세팅
    List<SongContent.SongItem> data;
    @Override
    public void onCreate() {
        Log.d("here is oncreate", "실행됨!");
        super.onCreate();

    }

    int currentPosition;
    MediaPlayer mp;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("here is onstartcommand", "실행됨!");
        currentPosition = intent.getIntExtra(Const.KEY_POSITION, -1);
        data = SongContent.ITEMS;
        if(mp == null) {
            mp = MediaPlayer.create(this, data.get(currentPosition).musicUri);
            mp.setLooping(false);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    // 정지된 상태에서 play
    int now;
    public void play(int position) {
        now = Const.STAT_ISPLAYING;

        if(mp.getCurrentPosition() == 0) {
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

    public boolean changeUI() {
        if(now == Const.STAT_ISPLAYING)
            return true;
        else
            return false;
    }

    public int getDuration() {
        return mp.getDuration();
    }

    public int getCurrentPosition() {
        return mp.getCurrentPosition();
    }

    @Override
    public void onDestroy() {
        mp.release();
        super.onDestroy();
    }
}
