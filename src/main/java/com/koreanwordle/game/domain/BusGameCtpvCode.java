package com.koreanwordle.game.domain;

public enum BusGameCtpvCode {

    SEOUL("11"), // 서울(11)
    JEONNAM_GWANGJU_SMC("12"), // 전남광주통합특별시(12)
    BUSAN("26"), // 부산광역시(26)
    DAEGU("27"), // 대구광역시(27)
    INCHEON("28"), // 인천광역시(28)
    DAEJEON("30"), // 대전광역시(30)
    ULSAN("31"), // 울산광역시(31)
    SEJONG("36"), // 세종특별자치시(36)
    GYEONGGI("41"), // 경기도(41)
    CHUNGCHEONGN("43"), // 충청북도(43)
    CHUNGCHEONGS("44"), // 충청남도(44)
    GYEONGSANGN("47"), // 경상북도(47)
    GYEONGSANGS("48"), // 경상남도(48)
    JEJU("50"), // 제주특별자치도(50)
    GANGWON("51"), // 강원특별자치도(51)
    JEONBUK("52"); // 전북특별자치도(52)

    private final String code;

    BusGameCtpvCode(String code) {
        this.code = code;
    }
}
