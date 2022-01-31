package com.example.chargingstation.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LatLong implements Serializable {
    public String lat;
    public String lng;

    public LatLong() {
    }

    public LatLong(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
