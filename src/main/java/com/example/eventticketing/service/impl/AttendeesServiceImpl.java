package com.example.eventticketing.service.impl;

import com.example.eventticketing.exception.NotFoundException;
import com.example.eventticketing.model.dto.request.AttendeesRequest;
import com.example.eventticketing.model.entity.Attendees;
import com.example.eventticketing.repository.AttendeesRepository;
import com.example.eventticketing.service.AttendeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AttendeesServiceImpl implements AttendeesService {
    private final AttendeesRepository attendeesRepository;
    @Override
    public List<Attendees> getAllAttendees(Integer page,Integer pageSize) {
        return attendeesRepository.getAllAttendees(page,pageSize);
    }

    @Override
    public Attendees addAttendee(AttendeesRequest attendeesRequest) {
        return attendeesRepository.addAttendee(attendeesRequest);
    }

    @Override
    public Attendees updateAttendee(Integer id, AttendeesRequest attendeesRequest) {
        Attendees attendees = attendeesRepository.updateAttendee(id,attendeesRequest);

        if(attendees == null) {
            throw new NotFoundException("Attendee ID " + id + " is not found!!!");
        }
        return attendees;
    }

    @Override
    public Attendees deleteAttendee(Integer id) {
        Attendees attendees = attendeesRepository.deleteAttendee(id);
        if(attendees == null){
            throw new NotFoundException("Attendee ID "+id+" is not found!!!");
        }
        return attendees;
    }

    @Override
    public Attendees getAttendeeById(Integer id) {
        Attendees attendees = attendeesRepository.getAttendeeById(id);
        if(attendees == null){
            throw new NotFoundException("Attendee ID "+id+" is not found!!!");
        }
        return attendees;
    }
}
