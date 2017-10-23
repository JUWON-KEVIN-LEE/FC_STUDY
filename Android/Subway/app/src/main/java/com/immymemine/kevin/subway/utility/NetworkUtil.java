package com.immymemine.kevin.subway.utility;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by quf93 on 2017-10-19.
 */

public class NetworkUtil {
    public static final String REALTIME_URL = "http://swopenapi.seoul.go.kr/api/subway/4f6e55564c64666b36366f55554f72/xml/realtimeStationArrival/0/100/";
    public static final String SUBWAY_URL = "http://openapi.seoul.go.kr:8088/4f6e55564c64666b36366f55554f72/json/SearchSTNBySubwayLineService/1/700/";

    public static String getRealTimeData(String s) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(s);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String temp = "";

                while ((temp = br.readLine()) != null) {
                    result.append(temp).append("\n");
                }
                br.close();
                isr.close();
            } else {
                Log.e("Server Error", con.getResponseCode() + "");
            }
            con.disconnect();
        } catch (IOException e) {
            Log.e("Error", e.toString());
        }

        return result.toString();
    }

    public static String getStationData(String s) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(s);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String temp = "";

                while ((temp = br.readLine()) != null) {
                    result.append(temp).append("\n");
                }
                br.close();
                isr.close();
            } else {
                Log.e("Server Error", con.getResponseCode() + "");
            }
            con.disconnect();
        } catch (IOException e) {
            Log.e("Error", e.toString());
        }

        return result.toString();
    }
}
