package com.sy.drawsyncserver.draw;

import java.util.List;

public record StrokeMessageDto(
        String meetingId,
        String senderId,
        String strokeId,
        String action,  // START, MOVE, END
        Long color,
        float width,
        List<StrokePointDto> points
) {}
