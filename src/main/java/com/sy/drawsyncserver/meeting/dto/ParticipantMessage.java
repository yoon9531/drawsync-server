package com.sy.drawsyncserver.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantMessage {
    private String meetingId;
    private String userId;
    private String type; // JOIN, LEAVE
}
