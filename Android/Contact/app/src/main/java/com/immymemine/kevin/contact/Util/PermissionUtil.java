package com.immymemine.kevin.contact.Util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by quf93 on 2017-09-26.
 */

public class PermissionUtil {
    // 버전 체크 > 동의 구하고 > 처리
    private int req_code;
    public PermissionUtil(int req_code) {
        this.req_code = req_code;
    }
    public void versionCheck(Activity activity) {
        if(Build.VERSION.SDK_INT >= M) {
            requestPermission(activity);
        }
        else {
            if(activity instanceof CallInit)
                ((CallInit)activity).init();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermission(Activity activity) {
        if(activity.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED &&
                activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                activity.checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            if(activity instanceof CallInit)
                ((CallInit)activity).init();
        } else {
            // 2.1 요청할 권한을 정의
            String[] permissions = {Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS};
            // 2.2 권한 요청
            activity.requestPermissions(permissions, req_code);
        }
    }

    public void requestResult(Activity activity, int req_code, int[] grantResults) {
        if(this.req_code == req_code) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                if (activity instanceof CallInit)
                    ((CallInit) activity).init();
                else {
                    Toast.makeText(activity.getBaseContext(), "You should implements CallInit Interface", Toast.LENGTH_LONG).show();
                    activity.finish();
                }
            } else {
                Toast.makeText(activity.getBaseContext(), "권한을 승인해야만 앱을 사용할 수 있습니다.", Toast.LENGTH_LONG).show();
                activity.finish();
            }
        }
    }

    public interface CallInit {
        public void init();
    }
}
