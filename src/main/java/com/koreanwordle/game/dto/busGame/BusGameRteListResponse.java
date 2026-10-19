package com.koreanwordle.game.dto.busGame;

import java.util.Map;

public record BusGameRteListResponse(
        Map<String, String> ctpvInfoList,
        Map<String, String> sggInfoList,
        Map<String, String> rteInfoList
) {
}
