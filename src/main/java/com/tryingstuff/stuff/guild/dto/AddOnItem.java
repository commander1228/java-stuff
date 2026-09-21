package com.tryingstuff.stuff.guild.dto;

import jakarta.validation.constraints.Positive;

public record AddOnItem(

        @Positive  long BlizzardId,
         @Positive long quantity
) {}
