package com.example.eventticketing.service.impl;

import com.example.eventticketing.model.dto.request.AttendeesRequest;
import com.example.eventticketing.model.entity.Attendees;
import com.example.eventticketing.repository.AttendeesRepository;
import com.example.eventticketing.service.AttendeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return attendeesRepository.updateAttendee(id,attendeesRequest);
    }

    @Override
    public Attendees deleteAttendee(Integer id) {
        return attendeesRepository.deleteAttendee(id);
    }

    @Override
    public Attendees getAttendeeById(Integer id) {
        return attendeesRepository.getAttendeeById(id);
    }
}
