package com.tryingstuff.stuff.ToDo.controller;

import java.util.List;

import com.tryingstuff.stuff.ToDo.dto.CreateTodoTypeRequest;
import com.tryingstuff.stuff.ToDo.entity.TodoType;
import com.tryingstuff.stuff.ToDo.service.TodoTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/todo-types")
public class TodoTypeController {
    private final TodoTypeService todoTypeService;

    @Autowired
    public TodoTypeController(TodoTypeService todoTypeService){
        this.todoTypeService = todoTypeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoType createTodoType(@Valid @RequestBody CreateTodoTypeRequest request){
        TodoType todoType = new TodoType();
        todoType.setName(request.name());

        return todoTypeService.createTodoType(todoType);
    }

    @GetMapping
    public List<TodoType> getAllTodoTypes() {
        return todoTypeService.getAllTodoTypes();
    }

    @GetMapping("/{id}")
    public TodoType getTodoTypeById(@PathVariable Long id){
        return todoTypeService.getTodoTypeById(id);
    }

    @DeleteMapping("/{id}")
    public TodoType deleteTodoType(@PathVariable Long id) {
        return todoTypeService.deleteTodoType(id);
    }

}
