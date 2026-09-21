package com.tryingstuff.stuff.guild.blizzapi;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "blizzard")
public record BlizzardProperties(
        OAuth oauth,
        String clientId,
        String clientSecret
) {
    public record OAuth(String tokenUrl){}
}
