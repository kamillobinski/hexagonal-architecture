package com.example.cash.machine.adapter;

import com.example.cash.machine.adapter.out.JpaLoadCashMachineAdapter;
import com.example.cash.machine.adapter.out.jpa.mapper.CashMachineMapper;
import com.example.cash.machine.adapter.out.jpa.repository.CashMachineEntityRepository;
import com.example.cash.machine.application.port.in.BalanceUseCase;
import com.example.cash.machine.application.port.in.DepositUseCase;
import com.example.cash.machine.application.port.in.WithdrawalUseCase;
import com.example.cash.machine.application.port.out.LoadCashMachinePort;
import com.example.cash.machine.application.port.out.SaveCashMachinePort;
import com.example.cash.machine.application.service.BalanceService;
import com.example.cash.machine.application.service.DepositService;
import com.example.cash.machine.application.service.WithdrawalService;
import com.example.cash.machine.domain.entities.CashMachine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class AppConfiguration {

    @Bean
    public DepositUseCase depositUseCase(LoadCashMachinePort loadCashMachinePort, SaveCashMachinePort saveCashMachinePort) {
        return new DepositService(loadCashMachinePort, saveCashMachinePort);
    }

    @Bean
    public WithdrawalUseCase withdrawalUseCase(LoadCashMachinePort loadCashMachinePort, SaveCashMachinePort saveCashMachinePort) {
        return new WithdrawalService(loadCashMachinePort, saveCashMachinePort);
    }

    @Bean
    public CashMachine cashMachine() {
        return CashMachine.builder().balance(BigDecimal.valueOf(255.30)).build();
    }

    @Bean
    public LoadCashMachinePort loadCashMachinePort(CashMachineMapper cashMachineMapper, CashMachineEntityRepository repository) {
        return new JpaLoadCashMachineAdapter(cashMachineMapper, repository);
    }

    @Bean
    public CashMachineMapper cashMachineMapper() {
        return new CashMachineMapper();
    }

    @Bean
    public BalanceUseCase balanceUseCase(LoadCashMachinePort loadCashMachinePort) {
        return new BalanceService(loadCashMachinePort);
    }
}
