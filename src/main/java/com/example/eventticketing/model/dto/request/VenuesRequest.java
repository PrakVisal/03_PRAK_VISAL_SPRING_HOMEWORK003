package com.example.eventticketing.model.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenuesRequest {
    @NotNull(message = "Venue's name cannot be null")
    @Size(min = 3, max = 20, message = "Venue's name must be between 3 and 20 characters")
    private String venueName;
    private String location;
}

