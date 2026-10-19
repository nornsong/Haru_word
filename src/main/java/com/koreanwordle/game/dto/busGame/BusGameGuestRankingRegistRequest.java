package com.koreanwordle.game.dto.busGame;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BusGameGuestRankingRegistRequest(
        String guestName,
        LocalDateTime record,
        String rteNm,
        String rteNo,
        String sggNm,
        String sggCd,
        LocalDate recordDate
) {
}
