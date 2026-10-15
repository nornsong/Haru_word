package com.koreanwordle.game.dto;

import java.util.List;
import java.util.Map;

public record BusGameBusStopIdResponse(
        Map<String, String> busRouteInfo,
        Map<String, List<BusGameStation>> stationsByRouteId
) { }