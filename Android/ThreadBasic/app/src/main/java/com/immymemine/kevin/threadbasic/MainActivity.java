package com.immymemine.kevin.threadbasic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

/**
 *  sub thread 를 이용해서 seekBar 회전...
 */
public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        final Rotater rotater = new Rotater(handler);
        rotater.start();
        seekBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotater.RUNNING=false;
            }
        });
    }

    public static final int ACTION_SET = 1004;
    // SeekBar 를 변경하는 Handler 작성..
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ACTION_SET:
                    float curRot = seekBar.getRotation();
                    seekBar.setRotation(curRot + 6);
                    break;
            }
        }
    };

}

class Rotater extends Thread {
    Handler handler;
    boolean RUNNING = true;
    public Rotater(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() { // start method 가 호출되면 실행되는 method..
        while(RUNNING) {
            for (int i = 0; i < 60; i++) {
                // send message to Handler...
                Message message = new Message();
                message.what = MainActivity.ACTION_SET;
                handler.sendMessage(message);
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
