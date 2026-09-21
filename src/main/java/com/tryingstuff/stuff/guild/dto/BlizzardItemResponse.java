package com.tryingstuff.stuff.guild.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BlizzardItemResponse(
        long id,
        String name,
        Quality quality
){
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Quality(
            String type,
            String name
    ){}
}
