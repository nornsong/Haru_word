package com.koreanwordle.game.service;

import com.koreanwordle.game.dto.BusGameSggResponse;
import com.koreanwordle.game.listener.BusGameListener;
import com.koreanwordle.game.listener.PublicApiSggRequest;
import com.koreanwordle.game.listener.PublicApiSggResponse;
import com.koreanwordle.game.repository.BusGameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusGameServiceImplTest {

    @Mock
    private BusGameRepository busGameRepository;

    @Mock
    private BusGameListener busGameListener;

    @Test
    void 시군구_전체_페이지를_조회하고_코드_기준으로_중복을_제거한다() {
        // given
        BusGameServiceImpl service =
                new BusGameServiceImpl(busGameRepository, busGameListener);

        String ctpvCd = "42";

        // 전체 건수 확인용 첫 요청
        when(busGameListener.getSgg(
                new PublicApiSggRequest(ctpvCd, 1, 1)
        )).thenReturn(response(
                1, 1, 2001, List.of()
        ));

        // 1페이지
        when(busGameListener.getSgg(
                new PublicApiSggRequest(ctpvCd, 1, 1000)
        )).thenReturn(response(
                1, 1000, 2001,
                List.of(
                        new PublicApiSggResponse.Item("42000", "강원도"),
                        new PublicApiSggResponse.Item("42110", "춘천시")
                )
        ));

        // 2페이지: 42000은 중복
        when(busGameListener.getSgg(
                new PublicApiSggRequest(ctpvCd, 2, 1000)
        )).thenReturn(response(
                2, 1000, 2001,
                List.of(
                        new PublicApiSggResponse.Item("42000", "강원도"),
                        new PublicApiSggResponse.Item("42130", "원주시")
                )
        ));

        // 3페이지
        when(busGameListener.getSgg(
                new PublicApiSggRequest(ctpvCd, 3, 1000)
        )).thenReturn(response(
                3, 1000, 2001,
                List.of(
                        new PublicApiSggResponse.Item("42150", "강릉시")
                )
        ));

        // when
        ResponseEntity<BusGameSggResponse> result =
                service.getBusGameSgg(ctpvCd);

        // then
        assertEquals(200, result.getStatusCode().value());
        assertNotNull(result.getBody());

        Map<String, String> sggByCode = result.getBody().sggByCode();

        assertEquals(
                Map.of(
                        "42000", "강원도",
                        "42110", "춘천시",
                        "42130", "원주시",
                        "42150", "강릉시"
                ),
                sggByCode
        );

        // 전체 건수 2001, 페이지 크기 1000 → 3페이지 호출 검증
        verify(busGameListener).getSgg(
                new PublicApiSggRequest(ctpvCd, 1, 1)
        );
        verify(busGameListener).getSgg(
                new PublicApiSggRequest(ctpvCd, 1, 1000)
        );
        verify(busGameListener).getSgg(
                new PublicApiSggRequest(ctpvCd, 2, 1000)
        );
        verify(busGameListener).getSgg(
                new PublicApiSggRequest(ctpvCd, 3, 1000)
        );
    }

    private PublicApiSggResponse response(
            int pageNo,
            int numOfRows,
            int totalCount,
            List<PublicApiSggResponse.Item> items
    ) {
        return new PublicApiSggResponse(
                new PublicApiSggResponse.Response(
                        new PublicApiSggResponse.Header("200", "SUCCESS"),
                        new PublicApiSggResponse.Body(
                                new PublicApiSggResponse.Items(items),
                                "JSON",
                                pageNo,
                                numOfRows,
                                totalCount
                        )
                )
        );
    }
}

