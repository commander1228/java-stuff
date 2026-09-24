package com.tryingstuff.stuff.volleyball.service;

import com.tryingstuff.stuff.volleyball.entity.Game;

import java.util.List;

public interface GameService {
    Game saveGame(Game game);

    List<Game> getAllGames();

    Game getGameById(Long id);

    Game deleteGame(Long id);

    List<Long> getAllGameIds();
}
