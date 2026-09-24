package com.tryingstuff.stuff.volleyball.dto;

import com.tryingstuff.stuff.volleyball.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlayerRequest(
        @NotBlank String name,
        @NotNull Gender gender) {
}
