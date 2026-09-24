package com.tryingstuff.stuff.volleyball.service;

import com.tryingstuff.stuff.volleyball.entity.Game;
import com.tryingstuff.stuff.volleyball.entity.GameAttendance;
import com.tryingstuff.stuff.volleyball.entity.Player;
import com.tryingstuff.stuff.volleyball.enums.Attendance;
import com.tryingstuff.stuff.volleyball.repository.GameAttendanceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public List<GameAttendance> getGameAttendancesByGameId(Long gameId) {
        return gameAttendanceRepository.findAllByGameIdWithPlayer(gameId);
    }

    @Override
    public GameAttendance deleteGameAttendance(Long id) {
        return null;
    }

    @Override
    public GameAttendance getGameAttendanceByGameAndPlayerId(Long gameId, Long playerId) {
        return gameAttendanceRepository.findByGame_IdAndPlayer_Id(gameId,playerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Attendance not found for game " + gameId + " and player " + playerId
                ));
    }

    @Override
    public GameAttendance updateGameAttendanceStatus(
            Long gameId,
            Long playerId,
            Attendance status
    ) {
        GameAttendance gameAttendance = getGameAttendanceByGameAndPlayerId(gameId, playerId);
        gameAttendance.setStatus(status);
        return gameAttendanceRepository.save(gameAttendance);
    }
}
