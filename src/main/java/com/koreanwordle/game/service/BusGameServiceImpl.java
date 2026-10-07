package com.koreanwordle.game.service;

import com.koreanwordle.game.dto.BusGameMakingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusGameServiceImpl implements BusGameService {

    private final BusGameRepository busGameRepo;

    @Override
    public ResponseEntity<BusGameMakingResponse> getMakingBusGameRoom () {

        // 2가지 정보를 제공해야함
        // 1 번째는 BusGameCtpvCode에 있는 지역 정보를 기반으로 API를 호출해서 나온 해당 지역들 정보
        // 2 번째는 그렇게 가져온 지역들 위치를 기반한 지역코드로 다시 API를 호출해서 버스 노선 정보를 가져오는 것


    }

}
