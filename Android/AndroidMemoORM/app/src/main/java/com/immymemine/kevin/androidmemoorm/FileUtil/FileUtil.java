package com.immymemine.kevin.androidmemoorm.FileUtil;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
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
     * @param bitmap 그림 파일
     * @throws IOException
     */
    Context context;
    public static void write(Context context, String fileName, Bitmap bitmap) throws IOException {
        FileOutputStream fos = null;
        try {
            // FileOutputStream fos = new FileOutputStream(file); Java
            // fos.write(memo.toBytes());
            fos = context.openFileOutput(fileName, MODE_PRIVATE);
            fos.write(bitmapToByteArray(bitmap));
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

    private static byte[] bitmapToByteArray( Bitmap bitmap ) {
        if(bitmap==null) {
            return null;
        }

        byte[] byteArray = null;

        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byteArray = stream.toByteArray();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArray;
    }

    public static Bitmap read(Context context, String fileName) throws IOException {
        Bitmap bitmap = null;
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(fileName);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (Exception e) {
            throw e;
        } finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch(Exception e) {
                    throw e;
                }
            }
        }

        return bitmap;
    }
}
