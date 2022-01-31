package com.example.chargingstation.web;

import com.example.chargingstation.model.enumerations.Type;
import com.example.chargingstation.service.StationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/stations")
public class StationController {


    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public String getStations(Model model){
        model.addAttribute("body","map_view");
        return "base";
    }

    @GetMapping("/new_location")
    public String getAddPage(Model model){
        model.addAttribute("body","add_map");
        model.addAttribute("types", Type.values());
        return "base";
    }

    @PostMapping("/new_location")
    public String submit(@RequestParam String cord,
                         @RequestParam String name,
                         @RequestParam String operatorName,
                         @RequestParam String type,
                         @RequestParam String city){
        stationService.save(cord, name, operatorName, type, city);
        return "redirect:/stations";
    }

}
