package com.example.cash.machine.application.service;

import com.example.cash.machine.application.port.in.BalanceUseCase;
import com.example.cash.machine.application.port.in.commands.BalanceCommand;
import com.example.cash.machine.application.port.out.LoadCashMachinePort;
import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.events.BalanceEvent;
import com.example.cash.machine.domain.exception.CashMachineException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class BalanceService implements BalanceUseCase {

    private final LoadCashMachinePort loadCashMachinePort;

    @Override
    public BalanceEvent balance(BalanceCommand balanceCommand) {
        Optional<CashMachine> cashMachineOptional = loadCashMachinePort.getById(1L);
        if (cashMachineOptional.isPresent()) {
            return cashMachineOptional.get().balance();
        } else {
            throw new CashMachineException("Cash machine not found");
        }
    }
}
