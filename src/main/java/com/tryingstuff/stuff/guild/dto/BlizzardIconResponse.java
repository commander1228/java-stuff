package com.tryingstuff.stuff.guild.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BlizzardIconResponse(
        List<IconUrl> assets
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record IconUrl(
    String key,
    String value
    ) {}
}
