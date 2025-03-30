package com.example.eventticketing.service.impl;

import com.example.eventticketing.exception.NotFoundException;
import com.example.eventticketing.model.entity.Attendees;
import com.example.eventticketing.model.entity.Events;
import com.example.eventticketing.model.dto.request.EventsRequest;
import com.example.eventticketing.model.entity.Venues;
import com.example.eventticketing.repository.AttendeesRepository;
import com.example.eventticketing.repository.EventsRepository;
import com.example.eventticketing.repository.VenuesRepository;
import com.example.eventticketing.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;
    private final AttendeesRepository attendeesRepository;
    private final VenuesRepository venuesRepository;

    @Override
    public List<Events> getAllEvents(Integer page, Integer pageSize) {
        return eventsRepository.getAllEvents(page, pageSize);
    }

    @Override
    public Events addEvent(EventsRequest eventsRequest) {
        Events events = eventsRepository.addEvent(eventsRequest);
        for (Integer attendeeId : eventsRequest.getAttendeeId()) {
            try {
                attendeesRepository.insertEventIdAndAttendeeId(events.getId(), attendeeId);
            } catch (DataIntegrityViolationException e) {
                eventsRepository.deleteEvent(events.getId());
                throw new NotFoundException("Attendee ID " + attendeeId + " is not found!!!");
            }
        }

        return eventsRepository.getEventById(events.getId());
    }

    @Override
    public Events updateEvent(Integer id, EventsRequest eventsRequest) {
        Venues venues = venuesRepository.getVenueById(eventsRequest.getVenues());
        if(venues == null){
            throw new NotFoundException("Venue ID " + eventsRequest.getVenues() + " is not found!!!");
        }
        Events events = eventsRepository.updateEvent(id, eventsRequest);
        if (events == null) {
            throw new NotFoundException("Event ID " + id + " is not found!!!");
        }
        //same event id from above
        Integer eventID = events.getId();
        //I just need to write another query to select only attendeeId for re-insert after failed updated
        List<Attendees> attendeesIdList = attendeesRepository.getAttendeeIdByEventId(id);
        //force converting to List<Integer> (because simple casting it's not working)
        List<Integer> attendeeIds = attendeesIdList.stream()
                .map(Attendees::getAttendeeId) // Make sure getAttendeeId() returns an Integer
                .toList();
        //delete
        attendeesRepository.deleteEventAttendeeTable(events.getId());
        for (Integer attendeeId : eventsRequest.getAttendeeId()) {
            try {
                attendeesRepository.insertEventIdAndAttendeeId(events.getId(), attendeeId);
            } catch (DataIntegrityViolationException e) {
                //re-delete
                attendeesRepository.deleteEventAttendeeTable(events.getId());
                //I just wanna re-insert the data that deleted after it's failed for updated
                for (Integer attendeeIDs : attendeeIds) {
                    attendeesRepository.insertEventIdAndAttendeeId(eventID, attendeeIDs);
                }
                throw new NotFoundException("Attendee ID " + attendeeId + " is not found!!!");
            }
        }
        return eventsRepository.updateEvent(events.getId(), eventsRequest);
    }

    @Override
    public Events deleteEvent(Integer id) {
        Events events = eventsRepository.deleteEvent(id);
        if (events == null) {
            throw new NotFoundException("Event ID " + id + " is not found!!!");
        }
        return events;
    }

    @Override
    public Events getEventById(Integer id) {
        Events events = eventsRepository.getEventById(id);
        if (events == null) {
            throw new NotFoundException("Event ID " + id + " is not found!!!");
        }
        return events;
    }
}
