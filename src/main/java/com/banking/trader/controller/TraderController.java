package com.banking.trader.controller;

import com.banking.trader.persist.entity.persist.Trader;
import com.banking.trader.services.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trading/traders")
public class TraderController {

    @Autowired
    private TraderService traderService;

    @PostMapping("/register")
    public ResponseEntity<Trader> register(@RequestBody Trader trader) {
        Trader created = traderService.registerTrader(trader);
        return new ResponseEntity<>(created, HttpStatus.CREATED); // Returns 201
    }

    @GetMapping("/all")
    public ResponseEntity<List<Trader>> getAll() {
        return ResponseEntity.ok(traderService.getAllTraders()); // Returns 200
    }

    @GetMapping
    public ResponseEntity<Trader> getByEmail(@RequestParam String email) {
        Trader trader = traderService.getTraderByEmail(email); // Returns 200 or 404
        return ResponseEntity.ok(trader);
    }

/*    @PutMapping
    public ResponseEntity<Trader> updateTrader(@RequestParam String email, @RequestBody UpdateTraderDTO dto) {
        Trader updated = traderService.updateTrader(email, dto); // Returns 200 or 404
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/add")
    public ResponseEntity<Trader> addMoney(@RequestParam String email, @RequestBody AddMoneyTraderDTO dto) {
        Trader updated = traderService.addMoney(email, dto); // Returns 200 or 404
        return ResponseEntity.ok(updated);
    }*/
}