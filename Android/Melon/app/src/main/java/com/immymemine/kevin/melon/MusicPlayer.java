package com.immymemine.kevin.melon;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicPlayer extends Service {
    MediaPlayer mp;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MusicPlayer() {
    }

    // 서비스가 호출될 때마다 아래 메소드가 호출된다
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int musicId = intent.getIntExtra("musicId", -1);
        if(musicId != -1) {
            mp = MediaPlayer.create(this, musicId);
            mp.setLooping(false); // 반복재생
            mp.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("onDestroy", "재생 중단");
        mp.pause();
        //mp.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
