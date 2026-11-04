package com.banking.trader.persistence.repo;

import com.banking.trader.persistence.entity.persist.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> {
    @Query(value = "SELECT * FROM TRADER WHERE EMAIL = :email", nativeQuery = true) //Native query
    Optional<Trader> findByEmail(String email);
    @Query("SELECT t FROM Trader t ORDER BY t.id ASC") //JPQL (Java Persistence Query Language)
    List<Trader> findAllByOrderByIdAsc();

}