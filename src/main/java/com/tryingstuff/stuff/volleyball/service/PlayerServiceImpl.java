package com.tryingstuff.stuff.volleyball.service;

import com.tryingstuff.stuff.volleyball.entity.Player;
import com.tryingstuff.stuff.volleyball.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(
            PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player deletePlayer(Long id) {
        Player playerToDelete = getPlayerById(id);
        playerRepository.delete(playerToDelete);
        return playerToDelete;
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("player not found: " + id));
    }
}
