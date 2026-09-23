package com.tryingstuff.stuff.volleyball.service;

import com.tryingstuff.stuff.volleyball.entity.Game;
import com.tryingstuff.stuff.volleyball.entity.GameAttendance;
import com.tryingstuff.stuff.volleyball.entity.Player;
import com.tryingstuff.stuff.volleyball.enums.Attendance;
import com.tryingstuff.stuff.volleyball.repository.GameAttendanceRepository;
import com.tryingstuff.stuff.volleyball.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameAttendanceServiceImpl implements GameAttendanceService {
    private final GameAttendanceRepository gameAttendanceRepository;
    private final PlayerService playerService;
    private final GameService gameService;

    public GameAttendanceServiceImpl(
            GameAttendanceRepository gameAttendanceRepository,
            PlayerService playerService,
            GameService gameService
    ){
        this.gameAttendanceRepository = gameAttendanceRepository;
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @Override
    public GameAttendance createGameAttendance(Attendance status, Long gameId, Long playerId) {
        GameAttendance gameAttendance = new GameAttendance();

        Player player = playerService.getPlayerById(playerId);
        Game game = gameService.getGameById(gameId);

        gameAttendance.setGame(game);
        gameAttendance.setPlayer(player);
        gameAttendance.setStatus(status);

        return gameAttendanceRepository.save(gameAttendance);
    }

    @Override
    public GameAttendance getGameAttendanceById(Long id) {
        return null;
    }

    @Override
    public List<GameAttendance> getAllGameAttendances() {
        return List.of();
    }

    @Override
    public List<GameAttendance> getGameAttendancesByGameId(Long id) {
        return List.of();
    }

    @Override
    public GameAttendance deleteGameAttendance(Long id) {
        return null;
    }

    @Override
    public GameAttendance getGameAttendanceByGameAndPlayerId(Long gameId, Long playerId) {
        return null;
    }

    @Override
    public GameAttendance updateGameAttendance(Long id) {
        return null;
    }
}
