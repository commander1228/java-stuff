package com.tryingstuff.stuff.volleyball.dto;

import com.tryingstuff.stuff.volleyball.enums.Attendance;
import com.tryingstuff.stuff.volleyball.enums.Gender;

import java.util.List;


public record GameDetailsResponse(
       Long id,
       String opponent,
       String gameTime,
       String gameDate,
       Long court,
       List<PlayerAttendanceResponse> players
   ) {
}
