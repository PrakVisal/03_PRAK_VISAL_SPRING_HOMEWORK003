package com.example.eventticketing.controller;

import com.example.eventticketing.model.dto.response.ApiResponse;
import com.example.eventticketing.model.entity.Events;
import com.example.eventticketing.model.dto.request.EventsRequest;
import com.example.eventticketing.model.entity.Venues;
import com.example.eventticketing.service.EventsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventsController {
    private final EventsService eventsService;
    @Operation(summary = "Get all Events")
    @GetMapping
    ResponseEntity<ApiResponse<List<Events>>> getAllEvents(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        ApiResponse<List<Events>> response = ApiResponse.<List<Events>>builder()
                .success(true)
                .message("Get all Events Successfully")
                .status(HttpStatus.OK)
                .payload(eventsService.getAllEvents(page,pageSize))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Operation(summary = "Add new Event")
    @PostMapping
    ResponseEntity<ApiResponse<Events>> addEvent(@RequestBody EventsRequest eventsRequest){
        ApiResponse<Events> response = ApiResponse.<Events>builder()
                .success(true)
                .message("Get all Events Successfully")
                .status(HttpStatus.OK)
                .payload(eventsService.addEvent(eventsRequest))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Operation(summary = "Update Event")
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<Events>> updateEvent(@PathVariable Integer id, @RequestBody EventsRequest eventsRequest){
        ApiResponse<Events> response = ApiResponse.<Events>builder()
                .success(true)
                .message("Updated Event Successfully")
                .status(HttpStatus.OK)
                .payload(eventsService.updateEvent(id,eventsRequest))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Operation(summary = "Delete Event")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Events>> deleteEvent(@PathVariable Integer id){
        ApiResponse<Events> response = ApiResponse.<Events>builder()
                .success(true)
                .message("Deleted Event Successfully")
                .status(HttpStatus.OK)
                .payload(eventsService.deleteEvent(id))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Operation(summary = "Get Event By ID")
    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Events>> getEventById(@PathVariable Integer id){
        ApiResponse<Events> response = ApiResponse.<Events>builder()
                .success(true)
                .message("Get Event by Id Successfully")
                .status(HttpStatus.OK)
                .payload(eventsService.getEventById(id))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
