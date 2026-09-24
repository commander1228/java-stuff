package com.tryingstuff.stuff.volleyball.dto;

import com.tryingstuff.stuff.volleyball.enums.Attendance;
import com.tryingstuff.stuff.volleyball.enums.Gender;

import java.time.LocalDate;
import java.util.List;


public record GameDetailsResponse(
       Long id,
       String opponent,
       String gameTime,
       LocalDate gameDate,
       Long court,
       List<PlayerAttendanceResponse> players
   ) {
}
