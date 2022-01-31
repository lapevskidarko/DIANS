package com.example.chargingstation.service;


import com.example.chargingstation.model.LatLong;
import com.example.chargingstation.model.Station;
import com.example.chargingstation.model.enumerations.Type;

import java.util.List;

public interface StationService {
    Station save(String latlong, String name, String operatorName, String type, String city);
    List<Station> search(String city);
    List<Station> findAll();

}
