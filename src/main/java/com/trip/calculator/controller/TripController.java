package com.trip.calculator.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trip.calculator.model.TripRequest;
import com.trip.calculator.service.TripService;

@RestController
public class TripController {

    @Autowired
    TripService tripService;

    @PostMapping("/calculate")
    public double calculate(@RequestBody TripRequest request) {
        return tripService.getCost(request.getStartLoc(), request.getEndLoc());
    }

}
