package com.example.eventticketing.repository;

import com.example.eventticketing.model.dto.request.AttendeesRequest;
import com.example.eventticketing.model.entity.Attendees;
import com.example.eventticketing.model.entity.Events;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeesRepository {
    @Select("""
            SELECT * FROM attendees OFFSET #{pageSize}*(#{page}-1) LIMIT #{pageSize}
            """)
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
    })
    List<Attendees> getAllAttendees(Integer page, Integer pageSize);

    @Select("""
            INSERT INTO attendees (attendee_name, email) VALUES (#{req.attendeeName},#{req.email}) RETURNING *
            """)
    @ResultMap("attendeeMapper")
    Attendees addAttendee(@Param("req") AttendeesRequest attendeesRequest);
    @Select("""
            UPDATE attendees SET attendee_name = #{req.attendeeName}, email = #{req.email} WHERE attendee_id = #{id} RETURNING *
            """)
    @ResultMap("attendeeMapper")
    Attendees updateAttendee(Integer id,@Param("req") AttendeesRequest attendeesRequest);
    @Select("""
            DELETE FROM attendees WHERE attendee_id = #{id} RETURNING *
            """)
    @ResultMap("attendeeMapper")
    Attendees deleteAttendee(Integer id);
    @Select("""
            SELECT * FROM attendees WHERE attendee_id = #{id}
            """)
    @ResultMap("attendeeMapper")
    Attendees getAttendeeById(Integer id);

    @Select("""
            SELECT * FROM attendees a JOIN event_attendee ea ON a.attendee_id = ea.attendee_id WHERE event_id = #{eventId}
            """)
    @ResultMap("attendeeMapper")
    List<Events> getAllAttendeesForEvent(Integer eventId);

    @Insert("""
            INSERT INTO event_attendee (event_id, attendee_id) VALUES (#{eventId},#{attendeeId})
            """)
    void insertEventIdAndAttendeeId(Integer eventId,Integer attendeeId);

    @Delete("""
            DELETE FROM event_attendee WHERE event_id = #{id}
            """)
    void deleteEventAttendeeTable (Integer id);
}
