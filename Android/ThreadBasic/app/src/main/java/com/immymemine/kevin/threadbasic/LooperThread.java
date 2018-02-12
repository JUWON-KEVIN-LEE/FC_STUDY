package com.immymemine.kevin.threadbasic;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by quf93 on 2017-11-05.
 */

public class LooperThread extends Thread {
    public Handler mHandler;

    public LooperThread() {

    }

    @Override
    public void run() {
        Looper.prepare();

        mHandler = new Handler() { // new 된 위치의 Thread 에 붙게된다.
            public void handleMessage(Message msg) {
                // process incoming messages here
            }
        };

        Looper.loop();

        // Looper.myLooper().quit(); 즉시 종료
        // Looper.myLooper().quitSafely(); Message Queue 를 비우고 종료
    }
}
