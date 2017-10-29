package com.immymemine.kevin.music_player.activities;

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

public class Players extends PermissionUtil{

    protected static boolean isBound = false;
    private static int mCurrentPosition = 0;
    public static List<MusicItem> data = new ArrayList<>();
    @Override
    public void init() {
        // set ITEMS;
        if(data.size() == 0)
            data = FileUtil.readMusicList(this);
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
