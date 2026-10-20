package com.koreanwordle.game.controller;

import com.koreanwordle.game.dto.busGame.*;
import com.koreanwordle.game.service.BusGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/busGame")
@RequiredArgsConstructor
public class BusGameController {

    private final BusGameService busGameService;

    @GetMapping("/chooseCtpv")
    public ResponseEntity<BusGameCtpvResponse> getBusGameCtpv () {
        return busGameService.getBusGameCtpv();
    }

    @GetMapping("/chooseSgg")
    public ResponseEntity<BusGameSggResponse> getBusGameSgg (@RequestBody String ctpvCd) {
        return busGameService.getBusGameSgg(ctpvCd);
    }

    @GetMapping("/chooseRteList")
    public ResponseEntity<BusGameRteListResponse> getBusGameRteList (@RequestBody BusGameRteListRequest request) {
        return busGameService.getBusGameRteList(request.ctpvCd(), request.sggCd());
    }

    @GetMapping("/chooseSttnList")
    public ResponseEntity<BusGameSttnListResponse> getBusGameSttnList (@RequestBody BusGameSttnListRequest request) {
        return busGameService.getBusGameSttnList(request.ctpvCd(), request.sggCd(), request.rteId());
    }

    @PostMapping("/startGuestBusGame")
    public ResponseEntity<BusGameGuestGameResponse> postGuestBusGameStart(@RequestBody String rteId) {
        return busGameService.postGuestBusGameStart(rteId);
    }

    @GetMapping("/startGuestBusGame/submitAnswer")
    public void postGuestBusGameSubmitAnswer(@RequestBody String submitAnswer) {}
}
