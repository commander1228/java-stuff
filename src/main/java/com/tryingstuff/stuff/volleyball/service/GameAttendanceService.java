package com.tryingstuff.stuff.volleyball.service;

import com.tryingstuff.stuff.volleyball.entity.GameAttendance;
import com.tryingstuff.stuff.volleyball.enums.Attendance;

import java.util.List;

public interface GameAttendanceService {

    GameAttendance createGameAttendance(Attendance status, Long gameId, Long playerId);

    GameAttendance getGameAttendanceById(Long id);

    List<GameAttendance> getAllGameAttendances();

    List<GameAttendance> getGameAttendancesByGameId(Long id);

    GameAttendance getGameAttendanceByGameAndPlayerId(Long gameId,Long playerId);

    GameAttendance updateGameAttendance(Long id);

    GameAttendance deleteGameAttendance(Long id);
}
