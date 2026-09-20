package com.tryingstuff.stuff.ToDo.service;

import java.time.LocalDate;
import java.util.List;

import com.tryingstuff.stuff.ToDo.repository.TodoRepository;
import com.tryingstuff.stuff.ToDo.entity.Todo;
import com.tryingstuff.stuff.ToDo.entity.TodoType;
import com.tryingstuff.stuff.ToDo.enums.TodoStatus;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoTypeService todoTypeService;

    public TodoServiceImpl(
            TodoRepository todoRepository,
            TodoTypeService todoTypeService){
        this.todoRepository = todoRepository;
        this.todoTypeService = todoTypeService;
    }

    @Override
    public Todo createTodo(Todo todo, Long todoTypeId) {
        todo.setCreatedDate(LocalDate.now());

        todo.setTodoType(findTodoType(todoTypeId));

        if(todo.getDescription() == null) {
            todo.setDescription("");
        }

        if (todo.getStatus() == null) {
            todo.setStatus(TodoStatus.NOT_STARTED);
        }

        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findByDeletedFalse();
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found: " + id));
    }

    @Override
    public Todo updateTodo(Long id, Todo todo, Long todoTypeId) {
        Todo existingTodo = getTodoById(id);

        existingTodo.setTodoType(findTodoType(todoTypeId));

        existingTodo.setTitle(todo.getTitle());
        existingTodo.setDescription(todo.getDescription() == null ? "" : todo.getDescription());
        existingTodo.setStatus(todo.getStatus());

        return todoRepository.save(existingTodo);
    }

    @Override
    public Todo deleteTodo(Long id) {
        Todo todo = getTodoById(id);
        todo.setDeleted(true);

        return todoRepository.save(todo);
    }

    private TodoType findTodoType(Long todoTypeId) {
        return todoTypeId == null ? null : todoTypeService.getTodoTypeById(todoTypeId);
    }

}
