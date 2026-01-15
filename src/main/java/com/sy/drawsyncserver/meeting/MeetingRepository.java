package com.sy.drawsyncserver.meeting;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MeetingRepository {
    private final Map<String, Meeting> meetings = new ConcurrentHashMap<>();

    public Meeting save(Meeting meeting) {
        meetings.put(meeting.getId(), meeting);
        return meeting;
    }

    public Optional<Meeting> findById(String id) {
        return Optional.ofNullable(meetings.get(id));
    }

    public boolean existsById(String id) {
        return meetings.containsKey(id);
    }

    public List<Meeting> findAll() {
        return new ArrayList<>(meetings.values());
    }

    public void deleteById(String id) {
        meetings.remove(id);
    }
}
