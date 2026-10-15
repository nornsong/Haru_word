package com.koreanwordle.game.repository;

import com.koreanwordle.game.domain.BusGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusGameRepository extends JpaRepository<BusGame, Long> {
}
