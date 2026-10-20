package com.koreanwordle.game.domain;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.Instant;

@Entity
public class BusGameRanking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tempPlayNm;

    @Column
    private Duration timeElapsed;

    @Column(unique = true)
    private String GameSection;

    @Column
    private Integer routePointCount;

    @Column
    private Instant startTime;
    @Column
    private Instant endTime;

    @Enumerated(EnumType.STRING)
    @Column
    private BusGameAccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_game_id")
    private BusGame busGame;

    public void setStartTime() {
        this.startTime = Instant.now();
    }
    public void setEndTime() {
        this.endTime = Instant.now();
    }

    public static BusGameRanking startGuest(String gameSectionId, BusGame firstStation) {
        BusGameRanking ranking = new BusGameRanking();
        ranking.GameSection = gameSectionId;
        ranking.accountType = BusGameAccountType.Unauthorized;
        ranking.routePointCount = 1;
        ranking.busGame = firstStation;
        return ranking;
    }
}
