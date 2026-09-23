package com.tryingstuff.stuff.volleyball.repository;

import com.tryingstuff.stuff.volleyball.entity.GameAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameAttendanceRepository extends JpaRepository<GameAttendance, Long> {
}
