package com.koreanwordle.game.listener;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BusGameListener {

    private final BusGameListener busGameListener;

    public PublicDataClient(BusGameListener busGameListener) {
        this.busGameListener = busGameListener;
    }

    public String getRouteList(
            String serviceKey,
            int pageNo,
            int numOfRows,
            String cityCode
    ) {

        return busGameListener.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("apis.data.go.kr")
                        .path("/xxxxx/getRouteInfo")
                        .queryParam("serviceKey", serviceKey)
                        .queryParam("pageNo", pageNo)
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("_type", "json")
                        .queryParam("cityCode", cityCode)
                        .build())
                .retrieve()
                .body(String.class);
    }

}
