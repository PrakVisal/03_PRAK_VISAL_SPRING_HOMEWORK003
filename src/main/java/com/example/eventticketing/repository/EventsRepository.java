package com.example.eventticketing.repository;

import com.example.eventticketing.model.entity.Events;
import com.example.eventticketing.model.dto.request.EventsRequest;
import com.example.eventticketing.model.entity.Venues;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventsRepository {
    @Select("""
            SELECT * FROM events OFFSET #{pageSize}*(#{page}-1) LIMIT #{pageSize}
            """)
    @Results(id = "eventsMapper", value = {
            @Result(property = "id", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venues", column = "venue_id", one = @One(select = "com.example.eventticketing.repository.VenuesRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id", many = @Many(select = "com.example.eventticketing.repository.AttendeesRepository.getAllAttendeesForEvent")),
    })
    List<Events> getAllEvents(Integer page, Integer pageSize);

    @Select("""
            INSERT INTO events (event_name,event_date,venue_id) VALUES (#{req.eventName},#{req.eventDate},#{req.venues}) RETURNING *
            """)
    @ResultMap("eventsMapper")
    Events addEvent(@Param("req") EventsRequest eventsRequest);

    @Select("""
            UPDATE events SET event_name = #{req.eventName}, event_date = #{req.eventDate}, venue_id = #{req.venues} WHERE event_id = #{id} RETURNING *
            """)
    @ResultMap("eventsMapper")
    Events updateEvent(Integer id, @Param("req") EventsRequest eventsRequest);

    @Select("""
            DELETE FROM events WHERE event_id = #{id} RETURNING *
            """)
    @ResultMap("eventsMapper")
    Events deleteEvent(Integer id);

    @Select("""
            SELECT * FROM events WHERE event_id = #{id}
            """)
    @ResultMap("eventsMapper")
    Events getEventById(Integer id);


}
