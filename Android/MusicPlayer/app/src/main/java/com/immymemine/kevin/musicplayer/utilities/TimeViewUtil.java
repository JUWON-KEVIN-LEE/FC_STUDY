package com.immymemine.kevin.musicplayer.utilities;

import java.text.SimpleDateFormat;

/**
 * Created by quf93 on 2017-10-13.
 */

public class TimeViewUtil {
    private SimpleDateFormat sdf;
    private static TimeViewUtil timeUtil = null;
    private TimeViewUtil() {}

    public static TimeViewUtil getInstance() {
        if(timeUtil == null)
            timeUtil = new TimeViewUtil();
        return timeUtil;
    }

    public String getDuration(int dr) {
        sdf = new SimpleDateFormat("mm:ss");
        String duration = sdf.format(dr);
        return duration;
    }

    public String getCurrentTime(int ct) {
        String currentTime = sdf.format(ct);
        return currentTime;
    }
}
