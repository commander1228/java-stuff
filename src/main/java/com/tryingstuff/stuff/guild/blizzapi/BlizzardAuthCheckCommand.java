package com.tryingstuff.stuff.guild.blizzapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BlizzardAuthCheckCommand implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(BlizzardAuthCheckCommand.class);

    private final BlizzardAuthService blizzardAuthService;
    private final ApplicationContext applicationContext;

    public BlizzardAuthCheckCommand(
            BlizzardAuthService blizzardAuthService,
            ApplicationContext applicationContext) {
        this.blizzardAuthService = blizzardAuthService;
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (!args.containsOption("check-blizzard-auth")) {
            return;
        }

        blizzardAuthService.getAccessToken();
        logger.info("Blizzard authentication succeeded.");

        int exitCode = SpringApplication.exit(applicationContext, () -> 0);
        System.exit(exitCode);
    }
}
