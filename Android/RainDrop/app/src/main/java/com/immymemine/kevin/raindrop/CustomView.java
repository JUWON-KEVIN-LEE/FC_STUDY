package com.immymemine.kevin.raindrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quf93 on 2017-10-10.
 */

public class CustomView extends View {
    Paint paint;
    List<MainActivity.RainDrop> rainDrops = new ArrayList<>();
    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(rainDrops.size() > 0) {
            for(int i=0; i<rainDrops.size(); i++) {
                MainActivity.RainDrop rainDrop = rainDrops.get(i);
                paint.setColor(rainDrop.color);
                paint.setAlpha(rainDrop.alpha);
                canvas.drawCircle(rainDrop.x, rainDrop.y, rainDrop.radius, paint);
            }

            if(rainDrops.size() > 200) {

            }
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
        rainDrops.add(rainDrop);
        rainDrop.start();
    }
}
