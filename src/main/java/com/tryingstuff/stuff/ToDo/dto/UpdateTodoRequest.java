package com.tryingstuff.stuff.ToDo.dto;

import com.tryingstuff.stuff.ToDo.TodoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTodoRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 255, message = "Title must be 255 characters or fewer")
        String title,

        String description,

        @NotNull(message = "Status is required")
        TodoStatus status
) {}
