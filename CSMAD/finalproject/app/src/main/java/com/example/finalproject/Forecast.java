package com.example.finalproject;

public class Forecast {
    String city;
    int lat, lon, aqi, o3;
    public Forecast(String city, String lat, String lon, String aqi, String o3)
    {
        this.city = city;
        this.lat = Integer.parseInt(lat);
        this.lon = Integer.parseInt(lon);
        this.aqi = Integer.parseInt(aqi);
        this.o3 = Integer.parseInt(o3);
    }
    public String getCity()
    {
        return city;
    }
    public int getLat()
    {
        return lat;
    }
    public int getLon()
    {
        return lon;
    }
    public int getAqi()
    {
        return aqi;
    }
    public int getO3()
    {
        return o3;
    }
}
