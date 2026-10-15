package com.koreanwordle.game.listener;

import java.time.LocalDate;

public record PublicApiBusStopRequest(
        int pageNo,
        int numOfRows,
        LocalDate toDay,
        String ctpv_cd,
        String sgg_cd
) {
}
