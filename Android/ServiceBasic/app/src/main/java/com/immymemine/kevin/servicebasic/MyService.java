package com.immymemine.kevin.servicebasic;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    public MyService() {
    }

    // 컴포넌트는 바인더를 통해 서비스에 접근할 수 있다
    class CustomBinder extends Binder {
        public CustomBinder() {

        }
        public MyService getService() {
            return MyService.this;
        }
    }

    IBinder binder = new CustomBinder();
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(" HERE IS...", "onBind(Intent intent)");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(" HERE IS...", "onUnbind(Intent intent)");
        return super.onUnbind(intent);
    }

    public int getTotal() {
        return total;
    }

    private int total = 0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(" HERE IS...", "onStartCommand(Intent intent, int flags, int startId)");
        for(int i=1; i<1000; i++) {
            total += i;
        }
        Toast.makeText(getBaseContext(), getTotal() + "", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.d(" HERE IS...", "onCreate() 최초 한번");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(" HERE IS...", "onDestroy()");
        super.onDestroy();
    }
}
