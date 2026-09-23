package com.tryingstuff.stuff.volleyball.repository;

import com.tryingstuff.stuff.volleyball.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
