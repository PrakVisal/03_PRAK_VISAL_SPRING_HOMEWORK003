package com.example.eventticketing.service.impl;

import com.example.eventticketing.model.entity.Events;
import com.example.eventticketing.model.dto.request.EventsRequest;
import com.example.eventticketing.model.entity.Venues;
import com.example.eventticketing.repository.AttendeesRepository;
import com.example.eventticketing.repository.EventsRepository;
import com.example.eventticketing.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;
    private final AttendeesRepository attendeesRepository;
    @Override
    public List<Events> getAllEvents(Integer page, Integer pageSize) {
        return eventsRepository.getAllEvents(page,pageSize);
    }

    @Override
    public Events addEvent(EventsRequest eventsRequest) {
        Events events = eventsRepository.addEvent(eventsRequest);
        for(Integer attendeeId : eventsRequest.getAttendeeId()){
            attendeesRepository.insertEventIdAndAttendeeId(events.getId(),attendeeId);
        }
        return eventsRepository.getEventById(events.getId());
    }

    @Override
    public Events updateEvent(Integer id, EventsRequest eventsRequest) {
        Events events = eventsRepository.updateEvent(id,eventsRequest);
        attendeesRepository.deleteEventAttendeeTable(events.getId());
        for(Integer attendeeId : eventsRequest.getAttendeeId()){
            attendeesRepository.insertEventIdAndAttendeeId(events.getId(),attendeeId);
        }
        return eventsRepository.updateEvent(events.getId(),eventsRequest);
    }

    @Override
    public Events deleteEvent(Integer id) {
        return eventsRepository.deleteEvent(id);
    }

    @Override
    public Events getEventById(Integer id) {
        return eventsRepository.getEventById(id);
    }
}
