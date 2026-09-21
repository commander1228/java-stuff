package com.tryingstuff.stuff.guild.blizzapi;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BlizzardProperties.class)
public class BlizzardConfig {
}
