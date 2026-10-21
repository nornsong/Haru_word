package com.koreanwordle.game.service;

import com.koreanwordle.game.dto.busGame.*;
import org.springframework.http.ResponseEntity;

public interface BusGameService {

    ResponseEntity<BusGameCtpvResponse> getBusGameCtpv();

    ResponseEntity<BusGameSggResponse> getBusGameSgg(String ctpvCd);

    ResponseEntity<BusGameRteListResponse> getBusGameRteList(String ctpvCd, String sggCd);

    ResponseEntity<BusGameSttnListResponse> getBusGameSttnList(String ctpvCd, String sggCd, String rteId);
    ResponseEntity<BusGameGuestGameResponse> postGuestBusGameStart(String rteId);

    ResponseEntity<BusGameSubmitAnswerResponse> getGuestBusGameSubmitAnswer(String submitAnswer, String gameSectionId);
}
