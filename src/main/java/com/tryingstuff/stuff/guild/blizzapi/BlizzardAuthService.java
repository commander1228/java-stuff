package com.tryingstuff.stuff.guild.blizzapi;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Service
public class BlizzardAuthService {
    private static final int REFRESH_BUFFER_SECONDS = 60;
    private static final Logger logger = LoggerFactory.getLogger(BlizzardAuthService.class);

    private final BlizzardProperties properties;
    private final RestClient restClient;

    private String accessToken;
    private Instant expiresAt = Instant.EPOCH;

    public BlizzardAuthService(
            BlizzardProperties properties,
            RestClient.Builder restClientBuilder){
        this.properties = properties;
        this.restClient = restClientBuilder.build();
    }

    public synchronized String getAccessToken(){
        if (accessToken == null || Instant.now().isAfter(expiresAt)){
            refreshAccessToken();
        }
        return accessToken;
    }

    private void refreshAccessToken() {
        if (!hasText(properties.clientId()) || !hasText(properties.clientSecret())) {
            throw new IllegalStateException("Blizzard OAuth credentials are missing.");
        }

        BlizzardTokenResponse response;
        try {
            response = restClient.post()
                    .uri(properties.oauth().tokenUrl())
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .headers(headers -> headers.setBasicAuth(
                            properties.clientId(),
                            properties.clientSecret()
                    ))
                    .body("grant_type=client_credentials")
                    .retrieve()
                    .body(BlizzardTokenResponse.class);
        } catch (RestClientResponseException exception) {
            logger.error(
                    "Blizzard OAuth token request failed: status={}",
                    exception.getStatusCode()
            );
            throw exception;
        }

        if (response == null
                || response.accessToken() == null
                || response.accessToken().isBlank()
                || response.expiresIn() == null) {
            throw new IllegalStateException(
                    "Blizzard OAuth returned an invalid access-token response."
            );
        }

        accessToken = response.accessToken();
        expiresAt = Instant.now().plusSeconds(
                Math.max(0, response.expiresIn() - REFRESH_BUFFER_SECONDS)
        );
        logger.info("Blizzard OAuth token acquired; expiresAt={}", expiresAt);
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    private record BlizzardTokenResponse(
            @JsonProperty("access_token") String accessToken,
            @JsonProperty("expires_in") Long expiresIn
    ) {
    }

}
