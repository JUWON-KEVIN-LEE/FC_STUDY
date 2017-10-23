package com.immymemine.kevin.bicycle;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by quf93 on 2017-10-18.
 */

public class Remote {
    public static String getData(String str) {
        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL(str);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String temp = "";
                while( (temp = br.readLine()) != null ) {
                    builder.append(temp).append("\n");
                }
            } else {
                Log.e("ServerError ", con.getResponseCode()+"");
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
        System.out.println(builder.toString());
        return builder.toString();
    }
}
