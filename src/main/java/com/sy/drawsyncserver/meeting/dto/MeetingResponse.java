package com.sy.drawsyncserver.meeting.dto;

import com.sy.drawsyncserver.meeting.Meeting;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor
public class MeetingResponse {
    private String id;
    private String title;
    private String hostId;
    private LocalDateTime createdAt;
    private Set<String> participants;
    private int participantCount;

    public static MeetingResponse from(Meeting meeting) {
        return new MeetingResponse(
                meeting.getId(),
                meeting.getTitle(),
                meeting.getHostId(),
                meeting.getCreatedAt(),
                meeting.getParticipants(),
                meeting.getParticipantCount()
        );
    }
}
