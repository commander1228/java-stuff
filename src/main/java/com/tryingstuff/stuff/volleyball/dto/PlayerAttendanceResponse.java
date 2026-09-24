package com.tryingstuff.stuff.volleyball.dto;

import com.tryingstuff.stuff.volleyball.enums.Attendance;
import com.tryingstuff.stuff.volleyball.enums.Gender;

public record PlayerAttendanceResponse(
        Long playerId,
        String playerName,
        Gender gender,
        Attendance attendanceStatus
) {
}
