package com.example.chargingstation.model;

import com.example.chargingstation.model.converters.LatLongConverter;
import com.example.chargingstation.model.enumerations.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = LatLongConverter.class)
    private LatLong latLong;

    private String name;

    private String operatorName;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String city;

    public Station(LatLong latLong, String name, String operatorName, Type type, String city) {
        this.latLong = latLong;
        this.name = name;
        this.operatorName = operatorName;
        this.type = type;
        this.city = city;
    }
}
