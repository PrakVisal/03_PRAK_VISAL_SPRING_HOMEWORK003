package com.example.eventticketing.controller;

import com.example.eventticketing.model.dto.request.AttendeesRequest;
import com.example.eventticketing.model.dto.response.ApiResponse;
import com.example.eventticketing.model.entity.Attendees;
import com.example.eventticketing.service.AttendeesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/attendee")
public class AttendeesController {
    private final AttendeesService attendeesService;

    @Operation(summary = "Get all Attendees")
    @GetMapping
    ResponseEntity<ApiResponse<List<Attendees>>> getAllAttendees(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        ApiResponse<List<Attendees>> response = ApiResponse.<List<Attendees>>builder()
                .success(true)
                .message("Get all Attendees Successfully")
                .status(HttpStatus.OK)
                .payload(attendeesService.getAllAttendees(page,pageSize))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Add new Attendee")
    @PostMapping
    ResponseEntity<ApiResponse<Attendees>> addAttendee(@RequestBody AttendeesRequest attendeesRequest) {
        ApiResponse<Attendees> response = ApiResponse.<Attendees>builder()
                .success(true)
                .message("Add Attendee Successfully")
                .status(HttpStatus.OK)
                .payload(attendeesService.addAttendee(attendeesRequest))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update Attendee")
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<Attendees>> updateAttendee(@PathVariable Integer id, @RequestBody AttendeesRequest attendeesRequest) {
        ApiResponse<Attendees> response = ApiResponse.<Attendees>builder()
                .success(true)
                .message("Updated Attendee Successfully")
                .status(HttpStatus.OK)
                .payload(attendeesService.updateAttendee(id,attendeesRequest))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete Attendee")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Attendees>> deleteAttendee(@PathVariable Integer id) {
        ApiResponse<Attendees> response = ApiResponse.<Attendees>builder()
                .success(true)
                .message("Deleted Attendee Successfully")
                .status(HttpStatus.OK)
                .payload(attendeesService.deleteAttendee(id))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get Attendee By ID")
    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Attendees>> updateAttendee(@PathVariable Integer id) {
        ApiResponse<Attendees> response = ApiResponse.<Attendees>builder()
                .success(true)
                .message("Get Attendee By ID Successfully")
                .status(HttpStatus.OK)
                .payload(attendeesService.getAttendeeById(id))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
