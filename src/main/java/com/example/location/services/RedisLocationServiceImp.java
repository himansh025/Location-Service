package com.example.location.services;

import com.example.location.dtos.DriverLocationDto;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;

public class RedisLocationServiceImp implements LocationService{

    private static String DRIVER_GRO_OPS_KEY;
    private static final Double SEARCH_RADIUS=5.0;

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLocationServiceImp(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Boolean saveDriverLocation(String driverId, Double latitude, Double longitude) {
        GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();
        geoOperations.add(
                DRIVER_GRO_OPS_KEY,
                new RedisGeoCommands.GeoLocation<>(
                        driverId
                        , new Point(
                        latitude,
                   longitude
                )));
        return true;
    }

    @Override
    public List<DriverLocationDto> getNearByDriver(Double latitude, Double longitude) {
        GeoOperations<String, String> geoOps = stringRedisTemplate.opsForGeo();
        Distance radius= new Distance(SEARCH_RADIUS, Metrics.KILOMETERS);
        Circle within= new Circle(new Point(latitude,longitude),radius);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results=  geoOps.radius(DRIVER_GRO_OPS_KEY,within);
        List<String>drivers= new ArrayList<>();
        for(GeoResult<RedisGeoCommands.GeoLocation<String>>result:results){
            drivers.add(result.getContent().getName());
        }
    }
}
