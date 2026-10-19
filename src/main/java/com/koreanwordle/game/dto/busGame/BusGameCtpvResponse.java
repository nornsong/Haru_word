package com.koreanwordle.game.dto.busGame;

import java.util.Map;

public record BusGameCtpvResponse(
        Map<String, String> ctpvInfoList
) {
}
