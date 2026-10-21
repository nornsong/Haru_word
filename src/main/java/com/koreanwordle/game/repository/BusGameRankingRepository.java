package com.koreanwordle.game.repository;

import com.koreanwordle.game.domain.BusGameRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusGameRankingRepository extends JpaRepository<BusGameRanking, Long> {

    @Query("""
            select b
            from BusGameRanking b
            where b.gameSection = :gameSectionId
    """)
    BusGameRanking gameSectionId(@Param("gameSectionId")String gameSectionId);



}
