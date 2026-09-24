package com.tryingstuff.stuff.volleyball.service;

import com.tryingstuff.stuff.volleyball.entity.Game;
import com.tryingstuff.stuff.volleyball.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(
            GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("player not found: " + id));
    }

    @Override
    public Game deleteGame(Long id) {
        Game gameToDelete = getGameById(id);
        gameRepository.delete(gameToDelete);
        return gameToDelete;
    }

    @Override
    public List<Long> getAllGameIds(){
        return gameRepository.findAllIds();
    }
}
