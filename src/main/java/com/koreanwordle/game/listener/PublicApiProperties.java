package com.koreanwordle.game.listener;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "public-api")
public record PublicApiProperties(
        String baseUrl,
        String serviceKey,
        String busStopUrl,
        String busStopServiceKey
) {
}
