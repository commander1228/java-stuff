package com.tryingstuff.stuff.guild.blizzapi;

import com.tryingstuff.stuff.guild.dto.BlizzardItemResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Service
public class BlizzardApiService {
    private static final Logger logger = LoggerFactory.getLogger(BlizzardApiService.class);

    private final BlizzardProperties properties;
    private final BlizzardAuthService blizzardAuthService;
    private final RestClient restClient;

    public BlizzardApiService(
            BlizzardProperties properties,
            BlizzardAuthService blizzardAuthService,
            RestClient.Builder restClientBuilder) {
        this.properties = properties;
        this.blizzardAuthService = blizzardAuthService;
        this.restClient = restClientBuilder.build();
    }

    public BlizzardItemResponse getItemById(long itemId) {
        if (itemId <= 0) {
            throw new IllegalArgumentException("Item ID must be positive.");
        }

        logger.info(
                "Fetching Blizzard item: itemId={}, namespace={}, locale={}",
                itemId,
                properties.api().classicEraNamespace(),
                properties.api().locale()
        );

        BlizzardItemResponse response;
        try {
            response = restClient.get()
                    .uri(
                            properties.api().baseUrl()
                                    + "/data/wow/item/{itemId}?namespace={namespace}&locale={locale}",
                            itemId,
                            properties.api().classicEraNamespace(),
                            properties.api().locale()
                    )
                    .headers(headers ->
                            headers.setBearerAuth(blizzardAuthService.getAccessToken()))
                    .retrieve()
                    .body(BlizzardItemResponse.class);
        } catch (RestClientResponseException exception) {
            logger.error(
                    "Blizzard item request failed: itemId={}, status={}",
                    itemId,
                    exception.getStatusCode()
            );
            throw exception;
        }

        if (response == null) {
            throw new IllegalStateException("Blizzard returned an empty item response.");
        }

        logger.info("Blizzard item fetched: itemId={}, name={}", response.id(), response.name());
        return response;
    }
}
