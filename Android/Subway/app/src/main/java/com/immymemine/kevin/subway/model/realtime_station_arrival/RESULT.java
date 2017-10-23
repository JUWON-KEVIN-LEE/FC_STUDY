package com.immymemine.kevin.subway.model.realtime_station_arrival;

/**
 * Created by quf93 on 2017-10-20.
 */

public class RESULT
{
    private String message;

    private String total;

    private String status;

    private String developerMessage;

    private String link;

    private String code;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getDeveloperMessage ()
    {
        return developerMessage;
    }

    public void setDeveloperMessage (String developerMessage)
    {
        this.developerMessage = developerMessage;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", total = "+total+", status = "+status+", developerMessage = "+developerMessage+", link = "+link+", code = "+code+"]";
    }
}


