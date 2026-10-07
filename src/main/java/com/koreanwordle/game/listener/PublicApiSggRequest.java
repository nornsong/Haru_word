package com.koreanwordle.game.listener;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "public-api")
public record PublicApiSggRequest(
        String baseUrl,
        String serviceKey,
        // 여러 페이지 짜리 api의 정보를 다 가져와야함
        String ctpv_cd
) {
}
