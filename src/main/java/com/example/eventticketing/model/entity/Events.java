package com.example.eventticketing.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Events {
    private Integer id;
    private String eventName;
    private LocalDateTime eventDate;
    private Venues venues;
    private List<Attendees> attendees;
}
