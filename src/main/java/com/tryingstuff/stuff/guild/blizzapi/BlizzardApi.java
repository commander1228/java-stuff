package com.tryingstuff.stuff.guild.blizzapi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BlizzardApi {
    private final BlizzardProperties properties;
    private final BlizzardAuthService blizzardAuthService;
    private final RestClient restClient;

    public BlizzardApi(
            BlizzardProperties properties,
            BlizzardAuthService blizzardAuthService,
            RestClient.Builder restClientBuilder) {
        this.properties = properties;
        this.blizzardAuthService = blizzardAuthService;
        this.restClient = restClientBuilder.build();
    }

    public String getItemById(long itemId) {
        if (itemId <= 0) {
            throw new IllegalArgumentException("Item ID must be positive.");
        }

        String response = restClient.get()
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
                .body(String.class);

        if (response == null || response.isBlank()) {
            throw new IllegalStateException("Blizzard returned an empty item response.");
        }

        return response;
    }
}
