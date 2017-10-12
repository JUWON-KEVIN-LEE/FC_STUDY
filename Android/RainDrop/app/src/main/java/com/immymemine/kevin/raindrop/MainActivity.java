package com.immymemine.kevin.raindrop;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Random;

public class MainActivity extends Activity {
    FrameLayout stage;
    CustomView customView;
    RainDrop rainDrop;
    static boolean runFlag = true;
    float width, height;
    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // remove notification bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set content view AFTER ABOVE sequence ( to avoid crash )
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        stage = (FrameLayout) findViewById(R.id.stage);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        customView = new CustomView(this);
        stage.addView(customView);
        //customView.runStage();
        random = new Random();
    }


    public int getRandomColor() {
        int[] colors = {Color.YELLOW, Color.GREEN, Color.BLUE, Color.RED, Color.CYAN, Color.MAGENTA};
        return colors[random.nextInt(6)];
    }

    boolean mode = false;
    int count = 0;
    public void modeChange(View view) {
        if(mode == false) {
            mode = true;
            new Thread() {
                public void run() {
                    while(mode) {
                        addRainDrop().start();
                        count++;
                        if(count > 100) {
                            customView.remove();
                            count -= 100;
                            try {
                                Thread.sleep(400);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        } else {
            mode = false;
        }

    }

    public RainDrop addRainDrop() {
        rainDrop = new RainDrop(random.nextInt((int) width), 0,
                random.nextInt(5), random.nextInt(20), getRandomColor(), random.nextInt(200));
        customView.addRainDrop(rainDrop);
        return rainDrop;
    }

    @Override
    protected void onDestroy() {
        runFlag  = false;
        super.onDestroy();
    }

    class RainDrop extends Thread{
        // 속성
        float x, y, speed, radius;
        int color, alpha;

        // 기능을 다했을 때까지 ... 생명주기
        float limit;
        int[] directions = {-1, 1};
        // 기능
        public void run() {
            int direction = directions[random.nextInt(2)];
            while(y < limit) {
                y = y  + (speed + 1) * 5;
                x = x + random.nextInt(5) * direction;
                customView.postInvalidate();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public RainDrop(float x, float y, float speed, float radius, int color, int alpha) {
            this.x = x; this.y = y; this.speed = speed; this.radius = radius + 10;
            this.color = color; this.alpha = alpha + 30; this.limit = height;
        }
    }
}



//    private void move() {
//        new Thread() {
//            public void run() {
//                while(runFlag) {
//                    customView.y = customView.y + 10;
//                    customView.postInvalidate();
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
//    }