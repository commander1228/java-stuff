package com.tryingstuff.stuff.volleyball.controller;


import com.tryingstuff.stuff.volleyball.dto.*;
import com.tryingstuff.stuff.volleyball.entity.Game;
import com.tryingstuff.stuff.volleyball.entity.Player;
import com.tryingstuff.stuff.volleyball.service.GameAttendanceService;
import com.tryingstuff.stuff.volleyball.service.GameService;
import com.tryingstuff.stuff.volleyball.service.GameOrchestrator;
import com.tryingstuff.stuff.volleyball.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/volleyball")
public class VolleyBallController {
    private final GameService gameService;
    private final GameAttendanceService gameAttendanceService;
    private final PlayerService playerService;
    private final GameOrchestrator gameOrchestrator;

    public VolleyBallController(
            GameService gameService,
            GameAttendanceService gameAttendanceService,
            PlayerService playerService,
            GameOrchestrator gameOrchestrator
            ){
        this.gameAttendanceService = gameAttendanceService;
        this.gameService = gameService;
        this.playerService = playerService;
        this.gameOrchestrator = gameOrchestrator;
    }

    @PostMapping("/game")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@Valid @RequestBody GameRequest gameRequest){
        Game game = gameService.saveGame(RequestMapper.game(gameRequest));
        gameOrchestrator.gameAdded(game);
        return game;
    }

    @PostMapping("/player")
    @ResponseStatus(HttpStatus.CREATED)
    public Player addPlayer(@Valid @RequestBody PlayerRequest playerRequest){
        Player player = playerService.savePlayer(RequestMapper.player(playerRequest));
        gameOrchestrator.playerAdded(player);
        return player;
    }

    @GetMapping("/game/{id}")
    public GameDetailsResponse getGameDetails(@PathVariable Long id){
        Game game = gameService.getGameById(id);

        return RequestMapper.gameDetailsResponse(
                game
                ,gameAttendanceService.getGameAttendancesByGameId(game.getId()));

    }

    @PutMapping("/attendance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePlayerStatus(@Valid @RequestBody AttendanceRequest attendanceRequest){
        gameAttendanceService.updateGameAttendanceStatus(
                attendanceRequest.gameId(),
                attendanceRequest.playerId(),
                attendanceRequest.attendance()
        );
    }
}
