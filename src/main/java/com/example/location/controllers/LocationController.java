package com.example.location.controllers;

import com.example.location.dtos.DriverLocationDto;
import com.example.location.dtos.NearbyDriverRequestDto;
import com.example.location.dtos.SaveDriverLocationRequestDto;
import com.example.location.services.LocationService;
import com.example.location.services.RedisLocationServiceImp;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping
    ResponseEntity<?> postLocation(@RequestBody SaveDriverLocationRequestDto saveDriverLocationRequestDto) {
        try {
            Boolean response= locationService.saveDriverLocation(saveDriverLocationRequestDto.getDriverId(),saveDriverLocationRequestDto.getLatitude(),saveDriverLocationRequestDto.getLongitude());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/nearby/driver")
    ResponseEntity<List<DriverLocationDto>> nearbyDriver(@RequestBody NearbyDriverRequestDto nearbyDriverRequestDto) {
        try {
        List<DriverLocationDto>response= locationService.getNearByDriver(nearbyDriverRequestDto.getLatitude(),nearbyDriverRequestDto.getLongitude());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
