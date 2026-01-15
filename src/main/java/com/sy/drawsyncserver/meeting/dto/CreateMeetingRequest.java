package com.sy.drawsyncserver.meeting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateMeetingRequest {
    private String title;
    private String hostId;
}
