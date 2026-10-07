package com.koreanwordle.game.controller;

import com.koreanwordle.game.dto.BusGameMakingResponse;
import com.koreanwordle.game.service.BusGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/busGame")
@RequiredArgsConstructor
public class BusGameController {

    private final BusGameService busGameService;

    @GetMapping("/makingGameRoom")
    public ResponseEntity<BusGameMakingResponse> getMakingBusGameRoom () {
        return busGameService.getMakingBusGameRoom();
    }
}
