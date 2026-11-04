package com.banking.trader.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI traderOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Banking App - Trader API")
                        .description("APIs to register traders, look them up, update their details and add money to their balance.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Rishabh Bhadauria")
                                .url("https://github.com/rishabhBhadauria/banking-app-trading")));
    }
}
