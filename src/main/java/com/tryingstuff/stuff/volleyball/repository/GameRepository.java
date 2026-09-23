package com.tryingstuff.stuff.volleyball.repository;

import com.tryingstuff.stuff.volleyball.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
