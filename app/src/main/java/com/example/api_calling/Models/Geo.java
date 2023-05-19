package com.example.api_calling.Models;

public class Geo {

    String lat,lag;

    public Geo(String lat, String lag) {
        this.lat = lat;
        this.lag = lag;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLag() {
        return lag;
    }

    public void setLag(String lag) {
        this.lag = lag;
    }
}
