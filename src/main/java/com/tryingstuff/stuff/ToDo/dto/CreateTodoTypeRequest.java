package com.tryingstuff.stuff.ToDo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTodoTypeRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name must be 255 characters or fewer")
        String name
){}
