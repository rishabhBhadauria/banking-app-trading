package com.banking.trader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Getter
@Setter
public class AddMoneyTraderDTO {
    @Schema(description = "Amount to add to the balance", example = "500.0")
    private Double amount;
}
