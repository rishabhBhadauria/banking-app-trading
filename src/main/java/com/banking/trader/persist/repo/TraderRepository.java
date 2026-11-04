package com.banking.trader.persist.repo;

import com.banking.trader.persist.entity.persist.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> {
    Optional<Trader> findByEmail(String email);
    List<Trader> findAllByOrderByIdAsc();
}