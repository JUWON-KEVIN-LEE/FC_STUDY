package com.immymemine.kevin.androidmemo.utility;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by quf93 on 2017-09-20.
 */

public class FileUtil {
    /**
     * 쓰기
     * @param context 컨텍스트
     * @param fileName 파일 이름
     * @param content 내용
     * @throws IOException
     */
    public static void write(Context context, String fileName, String content) throws IOException {
        FileOutputStream fos = null;
        try {
            // FileOutputStream fos = new FileOutputStream(file); Java
            // fos.write(memo.toBytes());
            fos = context.openFileOutput(fileName, MODE_PRIVATE);
            fos.write(content.getBytes());
            Log.d("WriteActivity", content);
        } catch (Exception e) {
            throw e;
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readAllFromFile(Context context, String fileName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = context.openFileInput(fileName);
            bis = new BufferedInputStream(fis);
            byte[] buf = new byte[1024];

            while(true) {
                int count = bis.read(buf);
                if(count==-1)
                    break;
                stringBuilder.append(new String(buf, 0, count));
            }
//            BufferedReader br = new BufferedReader(new FileReader(fileName)); text file 일 때 String 을 읽어올 때
        } catch (Exception e) {
            throw e;
        } finally {
            if(bis!=null) {
                try {
                    bis.close();
                } catch(Exception e) {
                    throw e;
                }
            }
            if(fis!=null) {
                try {
                    fis.close();
                } catch(Exception e) {
                    throw e;
                }
            }
        }

        return stringBuilder.toString();
    }
}
