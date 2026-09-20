package com.tryingstuff.stuff.ToDo.service;

import com.tryingstuff.stuff.ToDo.entity.TodoType;

import java.util.List;

public interface TodoTypeService {
    TodoType createTodoType(TodoType todoType);

    List<TodoType> getAllTodoTypes();

    TodoType getTodoTypeById(Long id);

    TodoType deleteTodoType(Long id);
}
