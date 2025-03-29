package com.example.eventticketing.model.dto.request;

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
public class EventsRequest {
    private String eventName;
    private LocalDateTime eventDate;
    private Integer venues;
    private List<Integer> attendeeId;
}
