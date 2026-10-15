package com.koreanwordle.game.listener;

public record PublicApiSggRequest(
        String ctpvCd,
        int pageNo,
        int numOfRows
) {
}