package com.example.eventticketing.controller;

import com.example.eventticketing.model.dto.request.VenuesRequest;
import com.example.eventticketing.model.dto.response.ApiResponse;
import com.example.eventticketing.model.entity.Venues;
import com.example.eventticketing.service.VenuesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venue")
@RequiredArgsConstructor
public class VenuesController {

    private final VenuesService venuesService;

    @Operation(summary = "Get all Venues")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venues>>> getAllVenues(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        ApiResponse<List<Venues>> response = ApiResponse.<List<Venues>>builder()
                .success(true)
                .message("Get All Venues Successfully")
                .status(HttpStatus.OK)
                .payload(venuesService.getAllVenues(page, pageSize))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get Venue by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Venues>> getVenueById(@PathVariable Integer id) {
        ApiResponse<Venues> response = ApiResponse.<Venues>builder()
                .success(true)
                .message("Get Venue By ID Successfully")
                .status(HttpStatus.OK)
                .payload(venuesService.getVenueById(id))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Add new Venue")
    @PostMapping
    public ResponseEntity<ApiResponse<Venues>> addVenue(@RequestBody VenuesRequest venuesRequest) {
        ApiResponse<Venues> response = ApiResponse.<Venues>builder()
                .success(true)
                .message("Add new Venue Successfully")
                .status(HttpStatus.OK)
                .payload(venuesService.addVenue(venuesRequest))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary = "Update Venue")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Venues>> updateVenue(@PathVariable Integer id, @RequestBody VenuesRequest venuesRequest) {
        ApiResponse<Venues> response = ApiResponse.<Venues>builder()
                .success(true)
                .message("Updated Venue Successfully")
                .status(HttpStatus.OK)
                .payload(venuesService.updateVenue(id,venuesRequest))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary = "Delete Venue")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Venues>> deleteVenue(@PathVariable Integer id) {
        ApiResponse<Venues> response = ApiResponse.<Venues>builder()
                .success(true)
                .message("Deleted Venue Successfully")
                .status(HttpStatus.OK)
                .payload(venuesService.deleteVenue(id))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
