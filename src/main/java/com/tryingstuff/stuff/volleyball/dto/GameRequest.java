package com.tryingstuff.stuff.volleyball.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record GameRequest(
        @NotBlank String opponent,
        @NotNull @Positive Long court,
        @NotBlank String gameTime,
        @NotNull LocalDate gameDate) {
}
