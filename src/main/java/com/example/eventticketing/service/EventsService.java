package com.example.eventticketing.service;

import com.example.eventticketing.model.entity.Events;
import com.example.eventticketing.model.dto.request.EventsRequest;
import com.example.eventticketing.model.entity.Venues;

import java.util.List;

public interface EventsService {
    List<Events> getAllEvents(Integer page, Integer pageSize);

    Events addEvent(EventsRequest eventsRequest);

    Events updateEvent(Integer id, EventsRequest eventsRequest);

    Events deleteEvent(Integer id);

    Events getEventById(Integer id);
}
