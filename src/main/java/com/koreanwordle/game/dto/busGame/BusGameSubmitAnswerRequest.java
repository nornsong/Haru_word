package com.koreanwordle.game.dto.busGame;

public record BusGameSubmitAnswerRequest(
        String submitAnswer,
        String gameSectionId
) {
}
