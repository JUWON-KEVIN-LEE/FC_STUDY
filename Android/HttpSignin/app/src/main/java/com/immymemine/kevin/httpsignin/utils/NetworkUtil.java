package com.immymemine.kevin.httpsignin.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by quf93 on 2017-10-25.
 */

public class NetworkUtil {

    public static String post(String address, String json) {
        String result = "";
        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            // data 전송
//            String temp = "";
//            for(String key : post_data.keySet()) {
//                temp += "&" + key + "=" + post_data.get(key);
//            }
//            temp = temp.substring(1);

            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();

            os.write(json.getBytes());
            os.flush();
            os.close();
            Log.d("con.getResponseCode()", String.valueOf(con.getResponseCode()));
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 여기서 부터는 파일에서 데이터를 가져오는 것과 동일
                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String temp = "";
                while ((temp = br.readLine()) != null) {
                    result += temp + "\n";
                }
                Log.d("result == ", result);
                br.close();
                isr.close();
            } else {
                Log.e("ServerError", con.getResponseCode()+"");
            }
            con.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getData(String string) {
        final StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(string);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            // 통신이 성공인지 체크
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 여기서 부터는 파일에서 데이터를 가져오는 것과 동일
                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String temp = "";
                while ((temp = br.readLine()) != null) {
                    result.append(temp).append("\n");
                }
                br.close();
                isr.close();
            } else {
                Log.e("ServerError", con.getResponseCode()+"");
            }
            con.disconnect();
        }catch(Exception e){
            Log.e("Error", e.toString());
        }
        return result.toString();
    }
}
