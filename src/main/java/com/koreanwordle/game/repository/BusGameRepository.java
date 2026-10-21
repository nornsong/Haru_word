package com.koreanwordle.game.repository;

import com.koreanwordle.game.domain.BusGame;
import com.koreanwordle.game.repository.projection.CtpvInfo;
import com.koreanwordle.game.repository.projection.RteInfo;
import com.koreanwordle.game.repository.projection.SggInfo;
import com.koreanwordle.game.repository.projection.SttnInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusGameRepository extends JpaRepository<BusGame, Long> {

    @Query("""
            select distinct
                b.ctpvCd as ctpvCd,
                b.ctpvNm as ctpvNm
            from BusGame b
            order by b.ctpvCd asc
    """)
    List<CtpvInfo> getAllCtpvInfo();

    @Query("""
            select distinct
                b.ctpvCd as ctpvCd,
                b.ctpvNm as ctpvNm,
                b.sggCd as sggCd,
                b.sggNm as sggNm
            from BusGame b
            where b.ctpvCd = :ctpvCd
            order by b.sggCd asc
    """)
    List<SggInfo> getAllSggInfo(@Param("ctpvCd")String ctpvCd);

    @Query("""
        select distinct
            b.ctpvCd as ctpvCd,
            b.ctpvNm as ctpvNm,
            b.sggCd as sggCd,
            b.sggNm as sggNm,
            b.rteId as rteId,
            b.rteNm as rteNm
        from BusGame b
        where b.ctpvCd = :ctpvCd
            and b.sggCd = :sggCd
            and b.rteId is not null
        order by b.rteId asc
    """)
    List<RteInfo> getAllRteInfo(@Param("ctpvCd")String ctpvCd,
                                @Param("sggCd")String sggCd
    );

    @Query("""
        select distinct
            b.ctpvCd as ctpvCd,
            b.ctpvNm as ctpvNm,
            b.sggCd as sggCd,
            b.sggNm as sggNm,
            b.rteId as rteId,
            b.rteNm as rteNm,
            b.sttnSeq as sttnSeq,
            b.sttnNm as sttnNm
        from BusGame b
        where b.ctpvCd = :ctpvCd
            and b.sggCd = :sggCd
            and b.rteId = :rteId
            and b.rteId is not null
        order by b.sttnSeq asc
    """)
    List<SttnInfo> getAllSttnInfo(@Param("ctpvCd")String ctpvCd,
                                  @Param("sggCd")String sggCd,
                                  @Param("rteId")String rteId
    );

    @Query(""" 
        select b
        from BusGame b
        where b.rteId = :rteId
            and b.sttnSeq is not null
        order by cast(b.sttnSeq as Long) asc, b.id asc
    """)
    List<BusGame> getRteInfo(@Param("rteId") String rteId);

    @Query(""" 
      select b
      from BusGame b
      where b.rteId = (
          select r.busGame.rteId
          from BusGameRanking r
          where r.gameSection = :gameSectionId
      )
      and b.sttnSeq is not null
      and(
        select count(s)
        from BusGame s
        where s.rteId = b.rteId
        and s.sttnSeq is not null
        and (
            cast(s.sttnSeq as Long) < cast(b.sttnSeq as Long)
            or(cast(s.sttnSeq as Long) = cast(b.sttnSeq as Long)
            and s.id < b.id
            )
        )
      ) = (:routePointCount - 1)
    """)
    Optional<BusGame> getSttnByGameSectionId(@Param("gameSectionId")String gameSectionId,
                                             @Param("routePointCount")Integer  routePointCount
    );

    @Query(""" 
        select count(b)
        from BusGame b
        where b.rteId = :rteId
        and b.sttnSeq is not null
    """)
    Integer countSttnByRteId(@Param("rteId") String rteId);

}
