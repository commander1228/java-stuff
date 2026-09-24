package com.tryingstuff.stuff.volleyball.repository;

import com.tryingstuff.stuff.volleyball.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("select g.id from Game g")
    List<Long> findAllIds();
}
