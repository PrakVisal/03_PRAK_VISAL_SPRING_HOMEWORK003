package com.example.eventticketing.repository;

import com.example.eventticketing.model.dto.request.VenuesRequest;
import com.example.eventticketing.model.entity.Venues;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenuesRepository {
    @Select("""
            SELECT * FROM venues OFFSET #{pageSize}*(#{page}-1) LIMIT #{pageSize}
            """)
    @Results(id = "venuesMapper", value = {
            @Result(property = "id", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
    })
    List<Venues> getAllVenues(Integer page, Integer pageSize);

    @Select("""
            SELECT * FROM venues WHERE venue_id = #{id}
            """)
    @ResultMap("venuesMapper")
    Venues getVenueById(Integer id);

    @Select("""
            
            INSERT INTO venues (venue_name,location) VALUES (#{req.venueName},#{req.location}) RETURNING *
            """)
    @ResultMap("venuesMapper")
    Venues addVenue(@Param("req") VenuesRequest venuesRequest);

    @Select("""
            UPDATE venues SET venue_name = #{venuesRequest.venueName}, location = #{venuesRequest.location} WHERE venue_id = #{id} RETURNING * 
            """)
    @ResultMap("venuesMapper")
    Venues updateVenue(Integer id, VenuesRequest venuesRequest);

    @Select("""
            DELETE FROM venues WHERE venue_id = #{id} RETURNING *
            """)
    @ResultMap("venuesMapper")
    Venues deleteVenue(Integer id);
}
