package com.sy.drawsyncserver.meeting;

import com.sy.drawsyncserver.meeting.dto.CreateMeetingRequest;
import com.sy.drawsyncserver.meeting.dto.JoinMeetingRequest;
import com.sy.drawsyncserver.meeting.dto.MeetingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;

    @PostMapping
    public ResponseEntity<MeetingResponse> createMeeting(@RequestBody CreateMeetingRequest request) {
        Meeting meeting = meetingService.createMeeting(request.getTitle(), request.getHostId());
        return ResponseEntity.ok(MeetingResponse.from(meeting));
    }

    @GetMapping
    public ResponseEntity<List<MeetingResponse>> getAllMeetings() {
        List<MeetingResponse> meetings = meetingService.getAllMeetings().stream()
                .map(MeetingResponse::from)
                .toList();
        return ResponseEntity.ok(meetings);
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<MeetingResponse> getMeeting(@PathVariable String meetingId) {
        return meetingService.getMeeting(meetingId)
                .map(meeting -> ResponseEntity.ok(MeetingResponse.from(meeting)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{meetingId}/join")
    public ResponseEntity<MeetingResponse> joinMeeting(
            @PathVariable String meetingId,
            @RequestBody JoinMeetingRequest request) {
        Meeting meeting = meetingService.joinMeeting(meetingId, request.getUserId());
        return ResponseEntity.ok(MeetingResponse.from(meeting));
    }

    @PostMapping("/{meetingId}/leave")
    public ResponseEntity<MeetingResponse> leaveMeeting(
            @PathVariable String meetingId,
            @RequestBody JoinMeetingRequest request) {
        Meeting meeting = meetingService.leaveMeeting(meetingId, request.getUserId());
        return ResponseEntity.ok(MeetingResponse.from(meeting));
    }
}
