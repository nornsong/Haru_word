package com.koreanwordle.game.domain;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;

@Entity
public class BusGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate oprYmd; // 운행일자 (yyyymmdd)

    @Column
    private BusGameCtpvCode ctpvCd; // 시도 코드 <-- Enum 해야겠는데?(수기 작성)

    @Column
    private String sggNm; // 시군구명
    @Column
    private Long sggCd; // 시군구 코드 <-- API로 모아오기

    @Column
    private Long rteId;

    @Column
    private String rteNo; //노선번호

    @Column
    private String rteNm; // 노선명

    @Column
    private String tempPlayNm; // 임시 플레이어 이름

    @Column
    private Duration timeElapsed;

}
