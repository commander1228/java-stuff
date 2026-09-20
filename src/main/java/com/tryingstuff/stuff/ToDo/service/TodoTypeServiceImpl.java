package com.tryingstuff.stuff.ToDo.service;

import java.util.List;

import com.tryingstuff.stuff.ToDo.entity.TodoType;
import com.tryingstuff.stuff.ToDo.repository.TodoTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TodoTypeServiceImpl implements TodoTypeService {
    private final TodoTypeRepository todoTypeRepository;

    public TodoTypeServiceImpl(TodoTypeRepository todoTypeRepository){
        this.todoTypeRepository = todoTypeRepository;
    }

    @Override
    public TodoType createTodoType(TodoType todoType) {

        String name = todoType.getName();

        if (name == null || name.isBlank()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Todo type name is required"
            );
        }

        name = name.trim();

        if (todoTypeRepository.existsByNameIgnoreCase(name)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A todo type with that name already exists"
            );
        }

        todoType.setName(name);

        return todoTypeRepository.save(todoType);
    }

    @Override
    public List<TodoType> getAllTodoTypes() {
        return todoTypeRepository.findByDeletedFalse();
    }

    @Override
    public TodoType getTodoTypeById(Long id) {
        return todoTypeRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Todo type not found: " + id
                ));
    }

    @Override
    public TodoType deleteTodoType(Long id) {
        TodoType todoType = getTodoTypeById(id);
        todoType.setDeleted(true);

        return todoTypeRepository.save(todoType);
    }
}
