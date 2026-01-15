package com.sy.drawsyncserver.meeting;

import com.sy.drawsyncserver.meeting.dto.MeetingResponse;
import com.sy.drawsyncserver.meeting.dto.ParticipantMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MeetingWebSocketController {
    private final MeetingService meetingService;

    @MessageMapping("/meeting/{meetingId}/join")
    @SendTo("/topic/meeting/{meetingId}")
    public MeetingResponse joinMeeting(
            @DestinationVariable String meetingId,
            ParticipantMessage message) {
        Meeting meeting = meetingService.joinMeeting(meetingId, message.getUserId());
        message.setType("JOIN");
        return MeetingResponse.from(meeting);
    }

    @MessageMapping("/meeting/{meetingId}/leave")
    @SendTo("/topic/meeting/{meetingId}")
    public MeetingResponse leaveMeeting(
            @DestinationVariable String meetingId,
            ParticipantMessage message) {
        Meeting meeting = meetingService.leaveMeeting(meetingId, message.getUserId());
        message.setType("LEAVE");
        return MeetingResponse.from(meeting);
    }
}
