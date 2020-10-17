package com.example.demo.controller;

import com.example.demo.model.Venue;
import com.example.demo.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/v1/venue")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class VenueController {

    @Autowired
    VenueService venueService;

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
        if (venueObtenido.isPresent())
        {
            return new ResponseEntity(venueObtenido, HttpStatus.OK);
        }
        return ResponseEntity.badRequest()
                .body("No existe esa venue");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteVenue(@PathVariable("id") Integer id)
    {
        if (venueService.getVenue(id).isPresent())
        {
        venueService.deleteVenue(id);
        return new ResponseEntity(HttpStatus.OK);
        }

        return ResponseEntity.badRequest()
                .body("No existe esa venue");
    }
    
    @GetMapping
    @ResponseBody
    public ResponseEntity getAllVenue ()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(venueService.getAllVenue());
    }
}
