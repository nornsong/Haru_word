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
}
