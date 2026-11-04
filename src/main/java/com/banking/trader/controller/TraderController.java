package com.banking.trader.controller;

import com.banking.trader.dto.AddMoneyTraderDTO;
import com.banking.trader.dto.UpdateTraderDTO;
import com.banking.trader.persistence.entity.persist.Trader;
import com.banking.trader.services.TraderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trading/traders")
@Tag(name = "Traders", description = "Manage traders and their balances")
public class TraderController {

    @Autowired
    private TraderService traderService;

    @Operation(summary = "Register a new trader")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Trader created"),
            @ApiResponse(responseCode = "400", description = "Invalid request or email already registered")
    })
    @PostMapping("/register")
    public ResponseEntity<Trader> register(@RequestBody Trader trader) {
        Trader created = traderService.registerTrader(trader);
        return new ResponseEntity<>(created, HttpStatus.CREATED); // Returns 201
    }

    @Operation(summary = "List all traders")
    @ApiResponse(responseCode = "200", description = "List of traders")
    @GetMapping("/all")
    public ResponseEntity<List<Trader>> getAll() {
        return ResponseEntity.ok(traderService.getAllTraders()); // Returns 200
    }

    @Operation(summary = "Get a trader by email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trader found"),
            @ApiResponse(responseCode = "404", description = "No trader with this email")
    })
    @GetMapping
    public ResponseEntity<Trader> getByEmail(
            @Parameter(description = "Trader's email", example = "john@example.com") @RequestParam String email) {
        Trader trader = traderService.getTraderByEmail(email); // Returns 200 or 404
        return ResponseEntity.ok(trader);
    }

    @Operation(summary = "Update a trader's name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trader updated"),
            @ApiResponse(responseCode = "404", description = "No trader with this email")
    })
    @PutMapping
    public ResponseEntity<Trader> updateTrader(
            @Parameter(description = "Trader's email", example = "john@example.com") @RequestParam String email,
            @RequestBody UpdateTraderDTO dto) {
        Trader updated = traderService.updateTrader(email, dto); // Returns 200 or 404
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Add money to a trader's balance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Balance updated"),
            @ApiResponse(responseCode = "404", description = "No trader with this email")
    })
    @PutMapping("/add")
    public ResponseEntity<Trader> addMoney(
            @Parameter(description = "Trader's email", example = "john@example.com") @RequestParam String email,
            @RequestBody AddMoneyTraderDTO dto) {
        Trader updated = traderService.addMoney(email, dto); // Returns 200 or 404
        return ResponseEntity.ok(updated);
    }
}
