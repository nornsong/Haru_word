package com.koreanwordle.game.listener;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PublicApiSggResponse(
        @JsonProperty("Response")
        Response response
) {

    public record Response(
            Header header,
            Body body
    ) {}

    public record Header(
            String resultCode,
            String resultMsg
    ) {}

    public record Body(
            Items items,
            String dataType,
            int pageNo,
            int numOfRows,
            int totalCount
    ) {}

    public record Items(
            List<Item> item
    ) {}

    public record Item(
            @JsonProperty("sgg_cd")
            String sggCd,

            @JsonProperty("sgg_nm")
            String sggNm
    ) {}
}
