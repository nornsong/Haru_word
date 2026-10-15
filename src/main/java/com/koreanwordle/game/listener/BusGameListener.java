package com.koreanwordle.game.listener;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.format.DateTimeFormatter;

@Component
public class BusGameListener {

    private final RestClient restClient;
    private final RestClient busStopRestClient;
    private final PublicApiProperties properties;

    public BusGameListener(PublicApiProperties properties) {
        this.properties = properties;

        this.restClient = RestClient.builder()
                .baseUrl(properties.baseUrl())
                .build();

        this.busStopRestClient = RestClient.builder()
                .baseUrl(properties.busStopUrl())
                .build();
    }

    public PublicApiSggResponse getSgg(
            PublicApiSggRequest request
    ) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getRegionalCode")
                        .queryParam("serviceKey", properties.serviceKey())
                        .queryParam("ctpv_cd", request.ctpvCd())
                        .queryParam("pageNo", request.pageNo())
                        .queryParam("numOfRows", request.numOfRows())
                        .queryParam("dataType", "JSON")
                        .build()
                )
                .retrieve()
                .body(PublicApiSggResponse.class);
    }

    public PublicApiBusStopResponse getBusStopRouteId(
            PublicApiBusStopRequest request
    ) {
        return busStopRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getBusRoutespecificStopInformation")
                        .queryParam("serviceKey", properties.busStopServiceKey())
                        .queryParam("ctpv_cd", request.ctpv_cd())
                        .queryParam("sgg_cd", request.sgg_cd())
                        .queryParam("pageNo", request.pageNo())
                        .queryParam("opr_ymd",  request.toDay().format(DateTimeFormatter.BASIC_ISO_DATE))
                        .queryParam("numOfRows", request.numOfRows())
                        .queryParam("dataType", "JSON")
                        .build()
                )
                .retrieve()
                .body(PublicApiBusStopResponse.class);
    }
}
