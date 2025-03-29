package com.example.eventticketing.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendees {
    private Integer attendeeId;
    private String attendeeName;
    private String email;
}
