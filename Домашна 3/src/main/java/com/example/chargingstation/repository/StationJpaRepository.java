package com.example.chargingstation.repository;

import com.example.chargingstation.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationJpaRepository extends JpaRepository<Station,Long> {
    List<Station> findAllByCityLikeIgnoreCase(String city);
}
