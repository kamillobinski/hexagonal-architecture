package com.example.cash.machine.adapter;

import com.example.cash.machine.application.port.in.DepositUseCase;
import com.example.cash.machine.application.port.in.WithdrawalUseCase;
import com.example.cash.machine.application.service.DepositService;
import com.example.cash.machine.application.service.WithdrawalService;
import com.example.cash.machine.domain.entities.CashMachine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class AppConfiguration {

    @Bean
    public DepositUseCase depositUseCase(CashMachine cashMachine) {
        return new DepositService(cashMachine);
    }

    @Bean
    public WithdrawalUseCase withdrawalUseCase(CashMachine cashMachine) {
        return new WithdrawalService(cashMachine);
    }

    @Bean
    public CashMachine cashMachine() {
        return CashMachine.builder().balance(BigDecimal.valueOf(255.30)).build();
    }
}
