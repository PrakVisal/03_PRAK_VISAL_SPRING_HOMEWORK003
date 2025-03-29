package com.example.eventticketing.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendeesRequest {
    @NotNull(message = "Attendee's name cannot be null")
    @Size(min = 3, max = 20, message = "Attendee's name must be between 3 and 20 characters")
    private String attendeeName;

    @Email(message = "Must be a well-formed email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;
}
