package com.example.chargingstation.service.Impl;

import com.example.chargingstation.model.LatLong;
import com.example.chargingstation.model.Station;
import com.example.chargingstation.model.enumerations.Type;
import com.example.chargingstation.repository.StationJpaRepository;
import com.example.chargingstation.service.StationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    private final StationJpaRepository stationJpaRepository;

    public StationServiceImpl(StationJpaRepository stationJpaRepository) {
        this.stationJpaRepository = stationJpaRepository;
    }

    @Override
    public Station save(String latlng, String name, String operatorName, String type, String city) {
        String [] parts = latlng.split(", ");
        return stationJpaRepository.save(new Station(new LatLong(parts[0],parts[1]),name,operatorName,Type.valueOf(type),city));
    }

    @Override
    public List<Station> search(String city) {
        return stationJpaRepository.findAllByCityLikeIgnoreCase("%"+city+"%");
    }

    @Override
    public List<Station> findAll() {
        return stationJpaRepository.findAll();
    }
}
