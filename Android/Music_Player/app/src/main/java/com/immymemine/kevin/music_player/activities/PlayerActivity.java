package com.immymemine.kevin.music_player.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.immymemine.kevin.music_player.model.MusicItem;
import com.immymemine.kevin.music_player.service.PlayerService;
import com.immymemine.kevin.music_player.utilities.FileUtil;
import com.immymemine.kevin.music_player.utilities.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quf93 on 2017-10-15.
 */

public class PlayerActivity extends PermissionUtil{
    private Intent intent;
    private PlayerService playerService;
    private boolean isBound = false;
    private static int mCurrentPosition = 0;
    public static List<MusicItem> data = new ArrayList<>();
    @Override
    public void init() {
        // set ITEMS;
        if(data.size() == 0)
            data = FileUtil.readMusicList(this);

        // bind to player service
        Log.d("intent setting", "========================");
        intent = new Intent(this, PlayerService.class);
        bindService(intent, con, Context.BIND_AUTO_CREATE);
    }

    private final ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayerService.CustomBinder binder = (PlayerService.CustomBinder) service;
            playerService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void unBind() {
        unbindService(con);
    }

    public void play(View view) {
        if(isBound) {
            playerService.play();
            isPlaying = playerService.getIsPlaying();
        }
    }

    public void pause(View view) {
        if(isBound) {
            playerService.pause();
            isPlaying = playerService.getIsPlaying();
        }
    }

    public void next(View view) {
        if(isBound) {
            playerService.next();
            mCurrentPosition = playerService.getmCurrentPosition();
        }
    }

    public void pre(View view) {
        if(isBound) {
            playerService.pre();
            mCurrentPosition = playerService.getmCurrentPosition();
        }
    }

    public void playByList(int position) {
        playerService.play(position);
        isPlaying = playerService.getIsPlaying();
        mCurrentPosition = playerService.getmCurrentPosition();
    }

    private static boolean isPlaying = false;
    public boolean getIsPlaying() {
        return isPlaying;
    }
    public int getmCurrentPosition() {
        return mCurrentPosition;
    }
    public int getPosition() {
        return playerService.getPosition();
    }

    public void seekTo(int position) {
        playerService.seekTo(position);
    }
}
