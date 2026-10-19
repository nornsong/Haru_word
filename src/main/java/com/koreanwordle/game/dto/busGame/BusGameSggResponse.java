package com.koreanwordle.game.dto.busGame;

import java.util.Map;

public record BusGameSggResponse(
        Map<String, String> ctpvInfoList,
        Map<String, String> sggInfoList
) {
}
