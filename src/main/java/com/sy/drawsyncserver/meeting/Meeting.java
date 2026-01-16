package com.sy.drawsyncserver.meeting;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meetings")
@Getter
@Setter
@NoArgsConstructor
public class Meeting {
    @Id
    private String id;

    private String title;

    private String hostId;

    private LocalDateTime createdAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "meeting_participants", joinColumns = @JoinColumn(name = "meeting_id"))
    @Column(name = "user_id")
    private Set<String> participants = new HashSet<>();

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
