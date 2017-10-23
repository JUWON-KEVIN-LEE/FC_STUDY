package com.immymemine.kevin.subway.model.realtime_station_arrival;

/**
 * Created by quf93 on 2017-10-19.
 */

public class RealtimeStationArrival {
    private RESULT RESULT;

    private Row[] row;

    public RESULT getRESULT ()
    {
        return RESULT;
    }

    public void setRESULT (RESULT RESULT)
    {
        this.RESULT = RESULT;
    }

    public Row[] getRow ()
    {
        return row;
    }

    public void setRow (Row[] row)
    {
        this.row = row;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [RESULT = "+RESULT+", row = "+row+"]";
    }
}