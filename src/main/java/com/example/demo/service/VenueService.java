package com.example.demo.service;


import com.example.demo.model.Venue;
import com.example.demo.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VenueService {

    @Autowired
    VenueRepository venueRepository;

    public Venue crearVenue (Venue venue)
    {
        return venueRepository.save(venue);
    }

    public Optional<Venue> getVenue (Integer id) { return venueRepository.findById(id); }
}

