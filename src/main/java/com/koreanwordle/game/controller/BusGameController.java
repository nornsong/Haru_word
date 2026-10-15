package com.koreanwordle.game.controller;

import com.koreanwordle.game.dto.BusGameBusStopRequest;
import com.koreanwordle.game.dto.BusGameSggResponse;
import com.koreanwordle.game.dto.BusGameBusStopIdResponse;
import com.koreanwordle.game.service.BusGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/busGame")
@RequiredArgsConstructor
public class BusGameController {

    private final BusGameService busGameService;

    @GetMapping("/chooseCtpv")
    public ResponseEntity<BusGameSggResponse> getBusGameSgg (@RequestBody String ctpvCd) {
        return busGameService.getBusGameSgg(ctpvCd);
    }

    @GetMapping("/choseSgg")
    public ResponseEntity<BusGameBusStopIdResponse> getBusGameRouteId (@RequestBody BusGameBusStopRequest request) {
        return busGameService.getBusGameBusStopRouteId(request.ctpvCd(), request.sggCd());
    }
}
