package com.immymemine.kevin.pic_memo_orm.Util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by quf93 on 2017-09-20.
 */

public class FileUtil {

    private static final String DIR = "/temp/picnote";

    public static void writeBitmap(Context context, String filename, Bitmap bitmap) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(filename, MODE_PRIVATE);
            fos.write(bitmapToByteArray(bitmap));
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    public static void writeFileToExternalStorage(Context context, String filename, Bitmap bitmap) throws IOException {
        FileOutputStream fos = null;

        String ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
        String realRoot = Environment.getRootDirectory().getAbsolutePath();
        String data = Environment.getDataDirectory().getAbsolutePath();

        try {
            // 1. 파일 저장을 위한 디렉토리를 지정한다.
            // 2. 체크해서 없으면 생성
            File dir = new File(ROOT + DIR);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            // 3. 해당 디렉토리에 파일 쓰기
            // 3.1 파일 검사
            File file = new File(ROOT +  DIR + "/" + filename);
            if(!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            fos.write(bitmapToByteArray(bitmap));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static byte[] bitmapToByteArray(Bitmap bitmap) {
        if (bitmap == null)
            return null;

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

    public static Bitmap bitmapReader(Context context, String filename) throws IOException {
        Bitmap bitmap = null;
        FileInputStream fis = null;

        try {
            fis = context.openFileInput(filename);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (Exception e) {
            throw e;
        } finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw e;
                }
            }

        }

        return bitmap;
    }

    public static Bitmap bitmapReaderFromExternalStorage(Context context, String filename) {
        Bitmap bitmap = null;
        FileInputStream fis = null;
        String ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
        File dir = new File(ROOT + DIR);
        if(!dir.exists()) {
            return null;
        }
        try {
            File file = new File(ROOT + DIR + "/" + filename);
            if(!file.exists()) {
                return null;
            }
            fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}