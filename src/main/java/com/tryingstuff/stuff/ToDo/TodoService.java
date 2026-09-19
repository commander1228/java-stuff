package com.tryingstuff.stuff.ToDo;

import java.util.List;

public interface TodoService {
    Todo createTodo(Todo todo);

    List<Todo> getAllTodos();

    Todo getTodoById(Long id);

    Todo updateTodo(Long id, Todo todo);

    Todo deleteTodo(Long id);
}
