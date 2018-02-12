package com.immymemine.kevin.threadbasic;

import android.os.Handler;
import android.os.Message;

/**
 * Created by quf93 on 2017-11-05.
 */

public class WorkerThread extends Thread {
    private Handler mHandler;
    boolean RUNNING = true;

    private static WorkerThread worker = null;
    private WorkerThread() {}
    public static WorkerThread getInstance() {
        if(worker == null)
            worker = new WorkerThread();

        return worker;
    }

    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

//    public void addHandler(Handler handler) {
//        mHandlers.add(handler);
//    }

    @Override
    public void run() { // start method 가 호출되면 실행되는 method..
        while(RUNNING) {
            for (int i = 0; i < 60; i++) {
                // Message Pool 에서 Message 객체를 가져와서 목표 handler 에 message.what 을 담아 객체 사용
                // 성능 이슈가 생길 수 있기 때문
                Message.obtain(mHandler, MainActivity.ACTION_SET).sendToTarget();

//                for(int j=0; j<mHandlers.size(); j++) {
//                    Handler handler = mHandlers.get(j);
//                    Message.obtain(handler, MainActivity.ACTION_SET).sendToTarget();
//                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // run 이 외의 method 들은 sub thread 에서 실행되지 않는다.
}
