package com.tryingstuff.stuff.ToDo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo createTodo(Todo todo) {
        todo.setCreatedDate(LocalDate.now());

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
    public Todo updateTodo(Long id, Todo todo) {
        Todo existingTodo = getTodoById(id);

        existingTodo.setTitle(todo.getTitle());
        existingTodo.setDescription(todo.getDescription());
        existingTodo.setStatus(todo.getStatus());

        return todoRepository.save(existingTodo);
    }

    @Override
    public Todo deleteTodo(Long id) {
        Todo todo = getTodoById(id);
        todo.setDeleted(true);

        return todoRepository.save(todo);
    }

}
