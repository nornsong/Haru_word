package com.koreanwordle.game.service;

import com.koreanwordle.game.dto.BusGameMakingResponse;
import org.springframework.http.ResponseEntity;

public interface BusGameService {

    ResponseEntity<BusGameMakingResponse> getMakingBusGameRoom();
}
