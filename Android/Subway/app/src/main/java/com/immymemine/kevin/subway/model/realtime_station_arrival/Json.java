package com.immymemine.kevin.subway.model.realtime_station_arrival;

/**
 * Created by quf93 on 2017-10-20.
 */

public class Json {
    private RealtimeStationArrival realtimeStationArrival;

    public RealtimeStationArrival getRealtimeStationArrival ()
    {
        return realtimeStationArrival;
    }

    public void setRealtimeStationArrival (RealtimeStationArrival realtimeStationArrival)
    {
        this.realtimeStationArrival = realtimeStationArrival;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [realtimeStationArrival = "+realtimeStationArrival+"]";
    }
}
