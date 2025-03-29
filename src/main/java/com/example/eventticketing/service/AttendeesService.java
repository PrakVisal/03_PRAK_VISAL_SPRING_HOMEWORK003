package com.example.eventticketing.service;

import com.example.eventticketing.model.dto.request.AttendeesRequest;
import com.example.eventticketing.model.entity.Attendees;

import java.util.List;

public interface AttendeesService {
    List<Attendees> getAllAttendees(Integer page,Integer pageSize);

    Attendees addAttendee(AttendeesRequest attendeesRequest);

    Attendees updateAttendee(Integer id, AttendeesRequest attendeesRequest);

    Attendees deleteAttendee(Integer id);

    Attendees getAttendeeById(Integer id);
}
