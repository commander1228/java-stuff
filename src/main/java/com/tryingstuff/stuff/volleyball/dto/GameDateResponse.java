package com.tryingstuff.stuff.volleyball.dto;

import java.time.LocalDate;

public record GameDateResponse(
        Long gameId,
        LocalDate date
) {}
