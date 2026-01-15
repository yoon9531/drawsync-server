package com.sy.drawsyncserver.meeting;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class Meeting {
    private String id;
    private String title;
    private String hostId;
    private LocalDateTime createdAt;
    private Set<String> participants = ConcurrentHashMap.newKeySet();

    public Meeting(String id, String title, String hostId) {
        this.id = id;
        this.title = title;
        this.hostId = hostId;
        this.createdAt = LocalDateTime.now();
    }

    public void addParticipant(String userId) {
        participants.add(userId);
    }

    public void removeParticipant(String userId) {
        participants.remove(userId);
    }

    public int getParticipantCount() {
        return participants.size();
    }
}
