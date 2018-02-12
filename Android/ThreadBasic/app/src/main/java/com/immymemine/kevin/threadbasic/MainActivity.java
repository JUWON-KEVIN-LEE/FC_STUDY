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
    WorkerThread worker;
    // LooperThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        thread = new LooperThread();
//        thread.start();

        worker = WorkerThread.getInstance();
        worker.setHandler(handler);
        worker.start();

//        worker.addHandler(handler);
//        worker.addHandler(thread.mHandler);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker.RUNNING=false;
            }
        });
    }

    public static final int ACTION_SET = 1004;
    // SeekBar 를 변경하는 Handler
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
