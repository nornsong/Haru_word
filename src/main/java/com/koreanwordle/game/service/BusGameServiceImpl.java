package com.koreanwordle.game.service;

import com.github.f4b6a3.ulid.UlidCreator;
import com.koreanwordle.game.domain.BusGame;
import com.koreanwordle.game.domain.BusGameRanking;
import com.koreanwordle.game.dto.busGame.*;
import com.koreanwordle.game.repository.BusGameRankingRepository;
import com.koreanwordle.game.repository.BusGameRepository;
import com.koreanwordle.game.repository.projection.CtpvInfo;
import com.koreanwordle.game.repository.projection.RteInfo;
import com.koreanwordle.game.repository.projection.SggInfo;
import com.koreanwordle.game.repository.projection.SttnInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BusGameServiceImpl implements BusGameService {

    private final BusGameRepository busGameRepo;
    private final BusGameRankingRepository busGameRankingRepo;

    @Override
    public ResponseEntity<BusGameCtpvResponse> getBusGameCtpv() {
        List<CtpvInfo> ctpvInfo = busGameRepo.getAllCtpvInfo();
        Map<String, String> ctpvInfoList = new HashMap<>();

        for(CtpvInfo info : ctpvInfo) {
            ctpvInfoList.putIfAbsent(
                    info.getCtpvCd(),
                    info.getCtpvNm()
            );
        }

        return ResponseEntity.ok(
                new BusGameCtpvResponse(ctpvInfoList)
        );
    }

    @Override
    public ResponseEntity<BusGameSggResponse> getBusGameSgg (String ctpvCd) {
        List<SggInfo> sggInfo = busGameRepo.getAllSggInfo(ctpvCd);
        Map<String, String> ctpvInfoList = new HashMap<>();
        Map<String, String> sggInfoList = new HashMap<>();

        for(SggInfo info : sggInfo) {
            ctpvInfoList.putIfAbsent(
                    info.getCtpvCd(),
                    info.getCtpvNm()
            );
            sggInfoList.putIfAbsent(
                    info.getSggCd(),
                    info.getSggNm()
            );
        }

        return ResponseEntity.ok(
                new BusGameSggResponse(ctpvInfoList, sggInfoList)
        );
    }

    @Override
    public ResponseEntity<BusGameRteListResponse> getBusGameRteList(String ctpvCd, String sggCd) {
        List<RteInfo> rteInfo = busGameRepo.getAllRteInfo(ctpvCd, sggCd);
        Map<String, String> ctpvInfoList = new HashMap<>();
        Map<String, String> sggInfoList = new HashMap<>();
        Map<String, String> rteInfoList = new HashMap<>();


        for(RteInfo info : rteInfo) {
            ctpvInfoList.putIfAbsent(
                    info.getCtpvCd(),
                    info.getCtpvNm()
            );
            sggInfoList.putIfAbsent(
                    info.getSggCd(),
                    info.getSggNm()
            );
            rteInfoList.putIfAbsent(
                    info.getRteId(),
                    info.getRteNm()
            );
        }

        return ResponseEntity.ok(
                new BusGameRteListResponse(ctpvInfoList, sggInfoList, rteInfoList)
        );
    }

    @Override
    public ResponseEntity<BusGameSttnListResponse> getBusGameSttnList(String ctpvCd, String sggCd, String rteId) {
        List<SttnInfo> rteInfo = busGameRepo.getAllSttnInfo(ctpvCd, sggCd, rteId);
        Map<String, String> ctpvInfoList = new HashMap<>();
        Map<String, String> sggInfoList = new HashMap<>();
        Map<String, String> rteInfoList = new HashMap<>();
        Map<String, String> sttnInfoList = new HashMap<>();


        for(SttnInfo info : rteInfo) {
            ctpvInfoList.putIfAbsent(
                    info.getCtpvCd(),
                    info.getCtpvNm()
            );
            sggInfoList.putIfAbsent(
                    info.getSggCd(),
                    info.getSggNm()
            );
            rteInfoList.putIfAbsent(
                    info.getRteId(),
                    info.getRteNm()
            );
            sttnInfoList.putIfAbsent(
                    info.getSttnSeq(),
                    info.getSttnNm()
            );
        }

        return ResponseEntity.ok(
                new BusGameSttnListResponse(
                        ctpvInfoList,
                        sggInfoList,
                        rteInfoList,
                        sttnInfoList
                )
        );
    }

    @Override
    @Transactional
    public ResponseEntity<BusGameGuestGameResponse> postGuestBusGameStart(String rteId) {


        List<BusGame> busGameList = busGameRepo.getRteInfo(rteId);
        BusGame firstStation = busGameList.getFirst();
        String gameSectionId = UlidCreator.getUlid().toString();

        BusGameRanking ranking = BusGameRanking.startGuest(gameSectionId, firstStation);

        busGameRankingRepo.save(ranking);

        List<BusGameGuestGameResponse.BusStationInfo> responseList =
                busGameList.stream()
                        .map(game ->
                                new BusGameGuestGameResponse.BusStationInfo(
                                        game.getRteId(),
                                        game.getRteNm(),
                                        game.getSttnSeq(),
                                        game.getSttnNm()
                                )
                        )
                        .toList();

        return ResponseEntity.ok(
                new BusGameGuestGameResponse(gameSectionId, responseList)
        );
    }
}
