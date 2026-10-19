package com.koreanwordle.game.domain;

import jakarta.persistence.*;

@Entity
public class BusGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ctpvNm; // 시도코드 명

    @Column(nullable = false)
    private String ctpvCd; // 시도 코드

    @Column
    private String sggNm; // 시군구명
    @Column
    private String sggCd; // 시군구 코드

    @Column
    private String rteId; // 노선 아이디

    @Column
    private String rteNm; // 노선명

    @Column
    private String sttnNm; // 정류장 이름

    @Column
    private String sttnSeq; // 정류장 번호
}
