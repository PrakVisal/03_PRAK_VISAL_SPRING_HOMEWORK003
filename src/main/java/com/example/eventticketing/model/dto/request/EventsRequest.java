package com.example.eventticketing.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventsRequest {
    @NotNull(message = "Event's name cannot be null")
    @Size(min = 3, max = 20, message = "Event's name must be between 3 and 20 characters")
    private String eventName;
    @NotNull(message = "Date time cannot be null")
    private LocalDateTime eventDate;
    @NotNull(message = "Venue Id cannot be null")
    private Integer venues;
    private List<Integer> attendeeId;
}
