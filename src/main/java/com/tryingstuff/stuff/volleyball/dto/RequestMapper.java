package com.tryingstuff.stuff.volleyball.dto;

import com.tryingstuff.stuff.volleyball.entity.Game;
import com.tryingstuff.stuff.volleyball.entity.GameAttendance;
import com.tryingstuff.stuff.volleyball.entity.Player;

import java.util.List;

public final class RequestMapper {
    public static Game game(GameRequest gameRequest){
        Game game = new Game();
        game.setOpponent(gameRequest.opponent());
        game.setGameDate(gameRequest.gameDate());
        game.setGameTime(gameRequest.gameTime());
        game.setCourt(gameRequest.court());

        return game;
    }

    public static Player player(PlayerRequest playerRequest){
        Player player = new Player();
        player.setName(playerRequest.name());
        player.setGender(playerRequest.gender());

        return player;
    }

    public static PlayerAttendanceResponse playerAttendanceResponse(GameAttendance gameAttendance){
        Player player = gameAttendance.getPlayer();
        return new PlayerAttendanceResponse(
                player.getId(),
                player.getName(),
                player.getGender(),
                gameAttendance.getStatus()
        );
    }

    public static GameDetailsResponse gameDetailsResponse(Game game, List<GameAttendance> gameAttendanceList){
        List<PlayerAttendanceResponse> players = gameAttendanceList.stream()
                .map(RequestMapper::playerAttendanceResponse).toList();

        return new GameDetailsResponse(
                game.getId(),
                game.getOpponent(),
                game.getGameTime(),
                game.getGameDate(),
                game.getCourt(),
                players
        );
    }
}
