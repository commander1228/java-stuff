package com.tryingstuff.stuff.ToDo.repository;

import java.util.List;
import java.util.Optional;

import com.tryingstuff.stuff.ToDo.entity.TodoType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoTypeRepository extends JpaRepository<TodoType, Long> {
    List<TodoType> findByDeletedFalse();
    boolean existsByNameIgnoreCase(String name);
    Optional<TodoType> findByIdAndDeletedFalse(Long id);
}
