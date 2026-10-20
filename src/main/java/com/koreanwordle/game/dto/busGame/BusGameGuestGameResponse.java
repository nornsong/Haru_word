package com.koreanwordle.game.dto.busGame;

import java.util.List;

public record BusGameGuestGameResponse(
        String gameSection,
        List<BusStationInfo> busGameList
) {
    public record BusStationInfo(
            String rteId,
            String rteNm,
            String sttnSeq,
            String sttnNm
    ) {}
}
