package com.immymemine.kevin.raindrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by quf93 on 2017-10-10.
 */

public class CustomView extends View {
    Paint paint;
    List<MainActivity.RainDrop> rainDrops = new ArrayList<>();
    private final Lock lock;
    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        lock = new ReentrantLock();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(rainDrops.size() > 0) {
//            try {
//                lock.lock();
            for (int i = 0; i < rainDrops.size(); i++) {
                MainActivity.RainDrop rainDrop = rainDrops.get(i);
                paint.setColor(rainDrop.color);
                paint.setAlpha(rainDrop.alpha);
                canvas.drawCircle(rainDrop.x, rainDrop.y, rainDrop.radius, paint);
            }
//            } finally {
//                lock.unlock();
//            }
        }
    }

//    public void runStage() {
//        new Thread() {
//            public void run() {
//                while(MainActivity.runFlag) {
//                    postInvalidate();
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
//    }

    public void addRainDrop(MainActivity.RainDrop rainDrop) {
//        try {
//            lock.lock();
            rainDrops.add(rainDrop);
//            rainDrop.start();
//        } finally {
//            lock.unlock();
//        }
    }

    public void remove() {
        for(int i=0; i<rainDrops.size(); i++)
            rainDrops.remove(rainDrops.get(i));
//            rainDrops.remove(i);
    }
}
