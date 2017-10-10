package com.immymemine.kevin.servicebasic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, MyService.class);
    }
    // 서비스 시작
    public void startService(View view) {
        startService(intent);
    }
    // 서비스 종료
    public void stopService(View view) {
        stopService(intent);
        isService = false;
    }

    public void bindService(View view) {
        bindService(intent, con, Context.BIND_AUTO_CREATE);
    }
    public void unbindService(View view) {
        unbindService(con);
    }
    // 서비스를 담아두는 변수
    MyService myService;
    boolean isService = false;
    // 서비스와의 연결 통로
    ServiceConnection con = new ServiceConnection() {
        // 서비스와 연결되는 순간 호출되는 함수
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isService = true;
            myService = ((MyService.CustomBinder)service).getService();
            Toast.makeText(MainActivity.this, myService.getTotal() + "", Toast.LENGTH_LONG).show();
        }
        // 서비스가 중된되거나 연결이 도중에 끊겼을 때 발생한다
        // 정상적으로 stop 이 호출되고, onDestroy 함수가 호출되면 아래 함수는 호출되지 않는다
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isService = false;
        }
    };
}
