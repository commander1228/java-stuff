package com.tryingstuff.stuff.volleyball.repository;

import com.tryingstuff.stuff.volleyball.entity.GameAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameAttendanceRepository extends JpaRepository<GameAttendance, Long> {
    @Query("""                                                                            
           select gameAttendance
           from GameAttendance gameAttendance
           join fetch gameAttendance.player
           where gameAttendance.game.id = :gameId
           order by gameAttendance.player.name
           """)
    List<GameAttendance> findAllByGameIdWithPlayer(@Param("gameId") Long gameId);

    Optional<GameAttendance> findByGame_IdAndPlayer_Id(
            Long gameId,
            Long playerId
    );
}
