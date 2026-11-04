package com.banking.trader.services;

import com.banking.trader.dto.AddMoneyTraderDTO;
import com.banking.trader.dto.UpdateTraderDTO;
import com.banking.trader.persistence.entity.persist.Trader;
import com.banking.trader.persistence.repo.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TraderService {

    @Autowired
    private TraderRepository traderRepository;

    public Trader registerTrader(Trader trader) {
        Optional<Trader> existing = traderRepository.findByEmail(trader.getEmail());
        if (existing.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trader with this email already exists");
        }
        trader.setId(null); // Always create a new row; never let the client target an existing id
        Timestamp now = new Timestamp(System.currentTimeMillis());
        trader.setCreatedAt(now);
        trader.setUpdatedAt(now);
        if (trader.getBalance() == null) {
            trader.setBalance(0.0);
        }
        return traderRepository.save(trader);
    }

    public List<Trader> getAllTraders() {
        return traderRepository.findAllByOrderByIdAsc();
    }

    public Trader getTraderByEmail(String email) {
        return traderRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trader not found"));
    }

    public Trader updateTrader(String email, UpdateTraderDTO dto) {
        Trader trader = getTraderByEmail(email); // Throws 404 if missing
        trader.setName(dto.getName());
        trader.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return traderRepository.save(trader);
    }

    public Trader addMoney(String email, AddMoneyTraderDTO dto) {
        Trader trader = getTraderByEmail(email); // Throws 404 if missing
        trader.setBalance(trader.getBalance() + dto.getAmount());
        trader.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return traderRepository.save(trader);
    }
}