package com.tryingstuff.stuff.ToDo.service;

import com.tryingstuff.stuff.ToDo.entity.Todo;

import java.util.List;

public interface TodoService {
    Todo createTodo(Todo todo, Long todoTypeId);

    List<Todo> getAllTodos();

    Todo getTodoById(Long id);

    Todo updateTodo(Long id, Todo todo, Long todoTypeId);

    Todo deleteTodo(Long id);
}
