package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(@RequestParam(name = "distillery", required=false) String distillery,
                                                       @RequestParam(name = "year", required=false) Integer year) {
        if (year != null && distillery == null) {
            return new ResponseEntity<>(whiskyRepository.findByYearIs(year), HttpStatus.OK);
        }else{
            if (year != null && distillery != null){
                return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(distillery, year), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity getWhiskies(@PathVariable Long id){
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

}
