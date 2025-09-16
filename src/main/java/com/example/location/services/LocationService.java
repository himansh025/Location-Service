package com.example.location.services;

import com.example.location.dtos.DriverLocationDto;

import java.util.List;

public interface LocationService {
    public Boolean saveDriverLocation(String driverId,Double latitude,Double longitude);

    List<DriverLocationDto> getNearByDriver(Double latitude, Double longitude);
}
