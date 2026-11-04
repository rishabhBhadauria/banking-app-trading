package com.banking.trader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTraderDTO {

    @Schema(description = "New name for the trader", example = "John Doe")
    private String name;

}
