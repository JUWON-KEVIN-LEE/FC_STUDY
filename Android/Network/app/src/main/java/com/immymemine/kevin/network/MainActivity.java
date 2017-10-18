package com.immymemine.kevin.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  네트워킹
 *  1. 권한 설정... not runtime permission
 *  2. 쓰레드 < 네트워크를 통한 데이터 이용은 sub thread 에서...
 *  3. httpUrlConnection < 내장 API
 *
 *  그 외...
 *  - Retrofit ( 내부에 Thread 포함 )
 *  - Rx ( 내부에 Thread 포함, Thread 관리기능 포함, 예외처리에 특화되어 있다 )
 */
public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("handleMessage", "run OK");
            msg.getCallback().run();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        NetworkThread thread  = new NetworkThread(handler);
        thread.start();

//        while(data == null) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        setTextViewDataFromNetwork(data);
    }

    /**
     *  httpURLConnection 사용
     *  1. URL 객체...
     *  2. 서버 연결 > httpURLConnection
     *  3. connection 설정 ( default = GET )
     *  4. Stream 을 통해서 데이터를 가져온다
     *  5. Stream 을 닫는다
     */
    String data;
    class NetworkThread extends Thread {
        Handler handler;
        public NetworkThread(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            try {
                URL url = null;
                url = new URL("http://fastcampus.co.kr");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                StringBuilder sb = new StringBuilder();
                // check
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    String temp = "";

                    while ((temp = br.readLine()) != null) {
                        sb.append(temp);
                    }

                    br.close();
                    isr.close();
                } else {
                    Log.e("Server Error", connection.getResponseCode() + "");
                }

                connection.disconnect();
                data = sb.toString();
                
                handler.post(new Runnable() { // runOnUiThread 내부 동작 방식
                    @Override
                    public void run() {
                        Log.e("post", "run OK");
                        setTextViewDataFromNetwork(data);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d("network", "connetion is OK");
        }
    }

    TextView mTextView;
    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
    }

    private void setTextViewDataFromNetwork(String data) {
        mTextView.setText(data);
    }
}