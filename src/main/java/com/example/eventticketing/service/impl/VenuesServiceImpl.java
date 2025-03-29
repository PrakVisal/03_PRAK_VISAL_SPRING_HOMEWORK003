package com.example.eventticketing.service.impl;

import com.example.eventticketing.exception.NotFoundException;
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
        Venues venues = venuesRepository.getVenueById(id);
        if(venues == null){
            throw new NotFoundException("Venue ID "+id+" is not found!!!");
        }
        return venues;
    }

    @Override
    public Venues addVenue(VenuesRequest venuesRequest) {
        return venuesRepository.addVenue(venuesRequest);
    }

    @Override
    public Venues updateVenue(Integer id, VenuesRequest venuesRequest) {
        Venues venues = venuesRepository.updateVenue(id,venuesRequest);
        if(venues == null){
            throw new NotFoundException("Venue ID "+id+" is not found!!!");
        }
        return venues;
    }

    @Override
    public Venues deleteVenue(Integer id) {
        Venues venues = venuesRepository.deleteVenue(id);
        if(venues == null){
            throw new NotFoundException("Venue ID "+id+" is not found!!!");
        }
        return venues;
    }
}
