package com.example.eventticketing.service;

import com.example.eventticketing.model.dto.request.VenuesRequest;
import com.example.eventticketing.model.entity.Venues;

import java.util.List;

public interface VenuesService {
    List<Venues> getAllVenues(Integer page,Integer pageSize);

    Venues getVenueById(Integer id);

    Venues addVenue(VenuesRequest venuesRequest);

    Venues updateVenue(Integer id, VenuesRequest venuesRequest);

    Venues deleteVenue(Integer id);
}
