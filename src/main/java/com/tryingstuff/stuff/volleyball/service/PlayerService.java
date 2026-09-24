package com.tryingstuff.stuff.volleyball.service;

import com.tryingstuff.stuff.volleyball.entity.Player;

import java.util.List;

public interface PlayerService {
    Player savePlayer(Player player);

    List<Player> getAllPlayers();

    Player deletePlayer(Long id);

    Player getPlayerById(Long id);

    List<Long> getAllPlayerIds();
}
