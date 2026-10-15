package com.koreanwordle.game.dto;

import java.util.Map;

public record BusGameSggResponse(
        Map<String, String> sggByCode
) {
}
