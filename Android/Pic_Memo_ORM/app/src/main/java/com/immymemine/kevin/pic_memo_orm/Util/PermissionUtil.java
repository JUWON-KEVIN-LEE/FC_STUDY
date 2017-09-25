package com.immymemine.kevin.pic_memo_orm.Util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quf93 on 2017-09-25.
 */

public class PermissionUtil{
    private int REQ_QODE = 400;
    private String[] permissions;

    public PermissionUtil(int req_code, String[] permissions) {
        this.REQ_QODE = req_code; this.permissions = permissions;
    }

    public void checkPermission(Activity activity) { // 버전 체크
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestPermission(activity);
        else
            callInit(activity);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermission(Activity activity) { // Permission 요청
        List<String> requires = new ArrayList<>();
        for(String permission : permissions) {
            if(activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                requires.add(permission); // 이미 허가되지 않은 요청들...
            }
        }
        if(requires.size() > 0) {
            String[] perms = requires.toArray(new  String[requires.size()]);
            activity.requestPermissions(perms, REQ_QODE); // 에 대해서 request..
        } else {
            callInit(activity);
        }
    }

    public void permissionResult(Activity activity, int requestCode, int[] grantResults) {
        if(requestCode == REQ_QODE) {
            for(int grant : grantResults) {
                if(grant != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(activity.getBaseContext(),"승인해줘라", Toast.LENGTH_SHORT).show();
                    break;
                }
                callInit(activity);
            }
        }
    }

    private static void callInit(Activity activity) {
        if(activity instanceof CallBack)
            ((CallBack)activity).init();
        else
            throw new RuntimeException("must implement this.CallBack");
    }

    public interface CallBack {
        public void init();
    }
}