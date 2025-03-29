package com.example.eventticketing.service.impl;

import com.example.eventticketing.model.dto.request.VenuesRequest;
import com.example.eventticketing.model.entity.Venues;
import com.example.eventticketing.repository.VenuesRepository;
import com.example.eventticketing.service.VenuesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class VenuesServiceImpl implements VenuesService {
    private final VenuesRepository venuesRepository;
    @Override
    public List<Venues> getAllVenues(Integer page, Integer pageSize) {
        return venuesRepository.getAllVenues(page,pageSize);
    }

    @Override
    public Venues getVenueById(Integer id) {
        return venuesRepository.getVenueById(id);
    }

    @Override
    public Venues addVenue(VenuesRequest venuesRequest) {
        return venuesRepository.addVenue(venuesRequest);
    }

    @Override
    public Venues updateVenue(Integer id, VenuesRequest venuesRequest) {
        return venuesRepository.updateVenue(id,venuesRequest);
    }

    @Override
    public Venues deleteVenue(Integer id) {
        return venuesRepository.deleteVenue(id);
    }
}
