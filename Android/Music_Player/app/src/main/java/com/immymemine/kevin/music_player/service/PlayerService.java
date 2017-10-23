package com.immymemine.kevin.music_player.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.immymemine.kevin.music_player.model.MusicItem;
import com.immymemine.kevin.music_player.utilities.FileUtil;

import java.io.IOException;
import java.util.List;

public class PlayerService extends Service implements MediaPlayer.OnCompletionListener {

    public PlayerService() {

    }

    private MediaPlayer mp;
    private List<MusicItem> data = null;
    private int mCurrentPosition = 0;
    private boolean isPlaying = false;
    @Override
    public void onCreate() {
        // get music list
        data = FileUtil.ITEMS;

        // init Media Player
        mp = MediaPlayer.create(this, data.get(mCurrentPosition).getMusicUri());
        mp.setLooping(false);
        mp.setOnCompletionListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private final IBinder binder = new CustomBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    public void play() {
        if(data.size() != 0) {
            mp.start();
            isPlaying = mp.isPlaying();
        }
    }

    public void play(int position) {
        if(data.size() != 0) {
            mCurrentPosition = position;
            if (isPlaying) {
                mp.stop();
                mp.reset();
                mp = MediaPlayer.create(this, data.get(mCurrentPosition).getMusicUri());
                mp.start();
                isPlaying = mp.isPlaying();
            } else {
                mp = MediaPlayer.create(this, data.get(mCurrentPosition).getMusicUri());
                mp.start();
                isPlaying = mp.isPlaying();
            }
        }
    }

    public void pause() {
        mp.pause();
        isPlaying = mp.isPlaying();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if(mp.getCurrentPosition() == mp.getDuration())
            next();
    }

    public void next() {
        // move position
        if(mCurrentPosition == data.size()-1) {
            mCurrentPosition = 0;
        } else {
            mCurrentPosition++;
        }

        if(isPlaying) {
            mp.stop();
            mp.reset();
            mp = MediaPlayer.create(this, data.get(mCurrentPosition).getMusicUri());
            mp.start();
        } else {
            mp.reset();
            mp = MediaPlayer.create(this, data.get(mCurrentPosition).getMusicUri());
            mp.start();
            isPlaying = mp.isPlaying();
        }
    }

    public void pre() {
        // move position
        if(mCurrentPosition == 0) {
            mCurrentPosition = data.size()-1;
        } else {
            mCurrentPosition--;
        }

        if(isPlaying) {
            mp.stop();
            mp.reset();
            mp = MediaPlayer.create(this, data.get(mCurrentPosition).getMusicUri());
            mp.start();
        } else {
            mp.reset();
            mp = MediaPlayer.create(this, data.get(mCurrentPosition).getMusicUri());

            try {
                mp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }
    public int getmCurrentPosition() { return mCurrentPosition; }
    public int getPosition() {
        return mp.getCurrentPosition();
    }

    public void seekTo(int position) {
        mp.seekTo(position);
    }

    public class CustomBinder extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }
    }

    public List<MusicItem> getData() {
        return data;
    }
}
