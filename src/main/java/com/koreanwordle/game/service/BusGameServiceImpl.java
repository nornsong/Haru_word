package com.koreanwordle.game.service;

import com.koreanwordle.game.dto.BusGameBusStopIdResponse;
import com.koreanwordle.game.dto.BusGameSggResponse;
import com.koreanwordle.game.dto.BusGameStation;
import com.koreanwordle.game.listener.*;
import com.koreanwordle.game.repository.BusGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BusGameServiceImpl implements BusGameService {

    private final BusGameRepository busGameRepo;
    private final BusGameListener busGameListener;

    private static final LocalDate BUS_STOP_QUERY_DATE =
            LocalDate.of(2026, 9, 18);

    protected PublicApiSggResponse getSgg(
            String ctpvCd,
            int pageNo,
            int numOfRows
    ) {

        PublicApiSggRequest request =
                new PublicApiSggRequest(
                        ctpvCd,
                        pageNo,
                        numOfRows
                );

        return busGameListener.getSgg(request);
    }

    protected PublicApiBusStopResponse getBusStopRouteId(
            int pageNo,
            int numOfRows,
            String ctpvCd,
            String sggCd
    ) {

        PublicApiBusStopRequest request =
                new PublicApiBusStopRequest(
                        pageNo,
                        numOfRows,
                        BUS_STOP_QUERY_DATE,
                        ctpvCd,
                        sggCd
                );

        return busGameListener.getBusStopRouteId(request);
    }

    @Override
    public ResponseEntity<BusGameSggResponse> getBusGameSgg (String ctpvCd) {
        int pageSize = 1000;

        PublicApiSggResponse firstResponse = getSgg(ctpvCd, 1, 1);

        int totalCount = firstResponse
                .response()
                .body()
                .totalCount();

        int totalPages = (totalCount + pageSize - 1) / pageSize;

        Map<String, String> sggByCode = new HashMap<>();

        for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
            PublicApiSggResponse response = getSgg(ctpvCd, pageNo, 1000);

            for (PublicApiSggResponse.Item item
                    : response.response().body().items().item()) {

                sggByCode.putIfAbsent(item.sggCd(), item.sggNm());
            }
        }

        return ResponseEntity.ok(
                new BusGameSggResponse(sggByCode)
        );
    }

    @Override
    public ResponseEntity<BusGameBusStopIdResponse> getBusGameBusStopRouteId(String ctpvCd, String sggCd) {
        int pageSize = 1000;

        PublicApiBusStopResponse firstResponse = getBusStopRouteId(
                1,
                1,
                ctpvCd,
                sggCd
        );

        int totalCount = firstResponse
                .response()
                .body()
                .totalCount();

        int totalPages = (totalCount + pageSize - 1) / pageSize;

        Map<String, String> busRouteInfo = new HashMap<>();
        Map<String, List<BusGameStation>> stationsByRouteId = new HashMap<>();

        for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
            PublicApiBusStopResponse response = getBusStopRouteId(
                    pageNo,
                    1000,
                    ctpvCd,
                    sggCd
            );

            for (PublicApiBusStopResponse.Item item
                    : response.response().body().items().item()) {
                busRouteInfo.putIfAbsent(item.routeId(), item.routeName());

                stationsByRouteId
                        .computeIfAbsent(
                                item.routeId(),
                                ignored -> new ArrayList<>()
                        )
                        .add(
                                new BusGameStation(
                                        item.stationSequence(),
                                        item.stationName()
                                )
                        );
            }
        }

        stationsByRouteId.values().forEach(stations ->
                stations.sort(
                        Comparator.comparingInt(BusGameStation::sequence)
                )
        );

        return ResponseEntity.ok(
                new BusGameBusStopIdResponse(
                        busRouteInfo,
                        stationsByRouteId
                )
        );
    }
}
