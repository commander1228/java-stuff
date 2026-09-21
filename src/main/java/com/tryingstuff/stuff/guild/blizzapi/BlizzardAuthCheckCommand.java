package com.tryingstuff.stuff.guild.blizzapi;

import java.util.List;

import com.tryingstuff.stuff.guild.dto.BlizzardItemResponse;
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
    private final BlizzardApiService blizzardApiService;
    private final ApplicationContext applicationContext;

    public BlizzardAuthCheckCommand(
            BlizzardAuthService blizzardAuthService,
            BlizzardApiService blizzardApiService,
            ApplicationContext applicationContext) {
        this.blizzardAuthService = blizzardAuthService;
        this.blizzardApiService = blizzardApiService;
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) {
        boolean checkedAuthentication = args.containsOption("check-blizzard-auth");
        boolean requestedItem = args.containsOption("get-classic-item");

        if (!checkedAuthentication && !requestedItem) {
            return;
        }

        if (checkedAuthentication) {
            blizzardAuthService.getAccessToken();
            logger.info("Blizzard authentication succeeded.");
        }

        if (requestedItem) {
            long itemId = getItemId(args.getOptionValues("get-classic-item"));
            BlizzardItemResponse item = blizzardApiService.getItemById(itemId);
            logger.info("Classic Era North America item response:\n{}", item);
        }

        int exitCode = SpringApplication.exit(applicationContext, () -> 0);
        System.exit(exitCode);
    }

    private long getItemId(List<String> itemIds) {
        if (itemIds == null || itemIds.size() != 1) {
            throw new IllegalArgumentException(
                    "Provide exactly one item ID with --get-classic-item=<item-id>."
            );
        }

        try {
            return Long.parseLong(itemIds.get(0));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Item ID must be a whole number: " + itemIds.get(0),
                    exception
            );
        }
    }
}
