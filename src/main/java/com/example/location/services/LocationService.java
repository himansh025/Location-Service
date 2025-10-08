package com.example.location.services;

import com.example.location.dtos.DriverLocationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {
    public Boolean saveDriverLocation(String driverId,Double latitude,Double longitude);

    List<DriverLocationDto> getNearByDriver(Double latitude, Double longitude);
}
