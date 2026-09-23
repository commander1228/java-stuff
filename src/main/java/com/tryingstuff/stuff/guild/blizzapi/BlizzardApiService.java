package com.tryingstuff.stuff.guild.blizzapi;

import com.tryingstuff.stuff.guild.dto.BlizzardIconResponse;
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

    private static final String ITEM_PATH = "/data/wow/item/{itemId}?namespace={namespace}&locale={locale}";
    private static final String ITEM_MEDIA_PATH = "/data/wow/media/item/{itemId}?namespace={namespace}&locale={locale}";

    public BlizzardApiService(
            BlizzardProperties properties,
            BlizzardAuthService blizzardAuthService,
            RestClient.Builder restClientBuilder) {
        this.properties = properties;
        this.blizzardAuthService = blizzardAuthService;
        this.restClient = restClientBuilder.build();
    }

    private <T> T getBlizzardItemResponse(
            String path,
            long itemId,
            Class<T> responseType
    ){
        try {
            T response = restClient.get()
                    .uri(
                            properties.api().baseUrl() + path, itemId,
                            properties.api().classicEraNamespace(),
                            properties.api().locale()
                    )
                    .headers(headers ->
                            headers.setBearerAuth(blizzardAuthService.getAccessToken()))
                    .retrieve()
                    .body(responseType);

            if (response == null) {
                throw new IllegalStateException(
                        "empty response"
                );
            }
            return response;
        } catch (RestClientResponseException exception){
            logger.error("blizzard api gave error: {}",exception.getStatusCode());
            throw exception;
        }
    }

    private long validateItemId(long itemId){
        if(itemId <= 0){
            throw new IllegalArgumentException("Item ID must be positive.");
        }
        return itemId;
    }

    public BlizzardItemResponse getItemById(long itemId) {
        validateItemId(itemId);
        BlizzardItemResponse response = getBlizzardItemResponse(ITEM_PATH,itemId,BlizzardItemResponse.class);
        logger.info("Blizzard item fetched: itemId={}, name={}", response.id(), response.name());
        return response;
    }

    public String getItemIconUrl(long itemId){
        validateItemId(itemId);
        BlizzardIconResponse response = getBlizzardItemResponse(ITEM_MEDIA_PATH,itemId,BlizzardIconResponse.class);
        if (response.assets() == null || response.assets().isEmpty()) {
            throw new IllegalStateException(
                    "Blizzard returned no media assets for item ID: " + itemId
            );
        }
        //returns icon url from inside json object
        return response.assets().stream()
                .filter(asset -> "icon".equals(asset.key()))
                .map(BlizzardIconResponse.IconUrl::value)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Blizzard returned no icon asset for item ID: " + itemId
                ));
    }
}
