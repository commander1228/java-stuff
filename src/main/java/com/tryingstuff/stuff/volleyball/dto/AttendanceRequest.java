package com.tryingstuff.stuff.volleyball.dto;

import com.tryingstuff.stuff.volleyball.enums.Attendance;
import jakarta.validation.constraints.NotNull;

public record AttendanceRequest(
        @NotNull Attendance attendance,
        @NotNull Long playerId,
        @NotNull Long gameId
) {}
