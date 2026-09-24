package com.tryingstuff.stuff.volleyball.repository;

import com.tryingstuff.stuff.volleyball.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("select p.id from Player p")
    List<Long> findAllIds();
}
