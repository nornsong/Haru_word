package com.koreanwordle.game.service;

import com.koreanwordle.game.dto.BusGameSggResponse;
import com.koreanwordle.game.dto.BusGameBusStopIdResponse;
import org.springframework.http.ResponseEntity;

public interface BusGameService {

    ResponseEntity<BusGameSggResponse> getBusGameSgg(String ctpvCd);

    ResponseEntity<BusGameBusStopIdResponse> getBusGameBusStopRouteId(String ctpvCd, String sggCd);
}
