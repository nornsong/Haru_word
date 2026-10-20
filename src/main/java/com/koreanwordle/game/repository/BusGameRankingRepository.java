package com.koreanwordle.game.repository;

import com.koreanwordle.game.domain.BusGameRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusGameRankingRepository extends JpaRepository<BusGameRanking, Long> {

}
