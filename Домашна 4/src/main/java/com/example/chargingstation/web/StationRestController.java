package com.example.chargingstation.web;

import com.example.chargingstation.model.Station;
import com.example.chargingstation.service.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationRestController {

    private final StationService stationService;


    public StationRestController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public List<Station> getStations(@RequestParam(required = false) String filter){
        if (filter != null && !filter.isEmpty())
            return stationService.search(filter);
        else
            return stationService.findAll();

    }
}
