package com.immymemine.kevin.subway.utility;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.immymemine.kevin.subway.model.station_info.Json_station;
import com.immymemine.kevin.subway.model.station_info.Row;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by quf93 on 2017-10-20.
 */

public class FileUtil {
    public String getDataFromAssets(Context context) {
        AssetManager am = context.getResources().getAssets();
        StringBuilder builder = new StringBuilder();
        try {
            InputStreamReader isr = new InputStreamReader(am.open("sub_info.txt"));
            BufferedReader br = new BufferedReader(isr);
            String temp = "";
            while( (temp = br.readLine()) != null ) {
                builder.append(temp).append("\n");
            }
            br.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public Row[] stringToRows(String s) {
        Gson gson = new Gson();
        Json_station json = gson.fromJson(s, Json_station.class);
        return json.getSearchSTNBySubwayLineService().getRow();
    }
}
