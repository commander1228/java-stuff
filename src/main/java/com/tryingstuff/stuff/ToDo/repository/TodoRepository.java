package com.tryingstuff.stuff.ToDo.repository;

import java.util.List;

import com.tryingstuff.stuff.ToDo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByDeletedFalse();
}
