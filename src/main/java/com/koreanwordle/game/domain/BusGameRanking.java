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

    @Column
    private String GameSection;

    @Column
    private Long routePointCount;

    @Column
    private Instant startTime;
    @Column
    private Instant endTime;

    @Enumerated(EnumType.STRING)
    @Column
    private BusGameAccountType accountType;

    public void setStartTime() {
        this.startTime = Instant.now();
    }
    public void setEndTime() {
        this.endTime = Instant.now();
    }
}
