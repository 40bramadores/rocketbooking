package com.example.demo.controller;

import com.example.demo.model.Venue;
import com.example.demo.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/v1/venue")
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping
    public ResponseEntity<Venue> crearVenue (@NonNull @RequestBody Venue venue)
    {
        Venue venueCreado = venueService.crearVenue(venue);
        return new ResponseEntity(venueCreado, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public ResponseEntity<?> getVenue (@PathVariable("id") Integer id)
    {
        Optional<Venue> venueObtenido = venueService.getVenue(id);
        return new ResponseEntity(venueObtenido, HttpStatus.OK);
    }
}
