package com.sy.drawsyncserver.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public Meeting createMeeting(String title, String hostId) {
        String meetingId = UUID.randomUUID().toString().substring(0, 8);
        Meeting meeting = new Meeting(meetingId, title, hostId);
        meeting.addParticipant(hostId);
        return meetingRepository.save(meeting);
    }

    public Optional<Meeting> getMeeting(String meetingId) {
        return meetingRepository.findById(meetingId);
    }

    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    public Meeting joinMeeting(String meetingId, String userId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회의입니다."));
        meeting.addParticipant(userId);
        return meeting;
    }

    public Meeting leaveMeeting(String meetingId, String userId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회의입니다."));
        meeting.removeParticipant(userId);
        return meeting;
    }
}
