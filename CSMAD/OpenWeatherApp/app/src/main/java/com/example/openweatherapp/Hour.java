package com.example.openweatherapp;

public class Hour {
    Long unixDate;
    Double temp;
    String timezone, desc;
    int imgID;
    public Hour(Long unixDate, String timezone, Double temp, String desc, int imgID)
    {
        this.unixDate = unixDate;
        this.timezone = timezone;
        this.temp = temp;
        this.desc = desc;
        this.imgID = imgID;
    }
    public Long getHour()
    {
        return unixDate;
    }
    public String getTimezone()
    {
        return timezone;
    }
    public String getTemp()
    {
        return String.valueOf(temp);
    }
    public String getDesc()
    {
        return desc;
    }
    public int getImgID()
    {
        return imgID;
    }
}
