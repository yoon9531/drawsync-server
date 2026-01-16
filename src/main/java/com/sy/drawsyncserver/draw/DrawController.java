package com.sy.drawsyncserver.draw;

import com.sy.drawsyncserver.meeting.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DrawController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MeetingService meetingService;

    @MessageMapping("/draw/{meetingId}/stroke")
    public void broadcastStroke(
            @DestinationVariable String meetingId,
            @Payload StrokeMessageDto message) {

        // 회의 존재 여부 검증
        if (meetingService.getMeeting(meetingId).isEmpty()) {
            return;
    }

    // 해당 회의방을 구독 중인 클라이언트들에게만 전송
        messagingTemplate.convertAndSend(
                "/topic/draw/" + meetingId,
                message
        );
    }
}
