package com.tryingstuff.stuff.volleyball.service;

import com.tryingstuff.stuff.volleyball.entity.Game;
import com.tryingstuff.stuff.volleyball.entity.Player;
import com.tryingstuff.stuff.volleyball.enums.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameOrchestrator {
    private final GameService gameService;
    private final GameAttendanceService gameAttendanceService;
    private final PlayerService playerService;

    public GameOrchestrator(
            GameService gameService,
            GameAttendanceService gameAttendanceService,
            PlayerService playerService){
        this.gameAttendanceService = gameAttendanceService;
        this.gameService = gameService;
        this.playerService = playerService;
    }

    public void gameAdded(Game game){
        List<Long> playerIds = playerService.getAllPlayerIds();
        Long gameId = game.getId();
        for (Long playerId : playerIds){
            gameAttendanceService.createGameAttendance(Attendance.UNDECIDED, gameId, playerId);
        }
    }

    public void playerAdded(Player player){
        List<Long> gameIds = gameService.getAllGameIds();
        Long playerId = player.getId();
        for (Long gameId : gameIds){
            gameAttendanceService.createGameAttendance(Attendance.UNDECIDED, gameId, playerId);
        }
    }
}
