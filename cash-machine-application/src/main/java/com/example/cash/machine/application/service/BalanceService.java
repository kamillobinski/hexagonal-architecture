package com.example.cash.machine.application.service;

import com.example.cash.machine.application.port.in.BalanceUseCase;
import com.example.cash.machine.application.port.in.commands.BalanceCommand;
import com.example.cash.machine.application.port.out.LoadCashMachinePort;
import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.events.BalanceEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BalanceService implements BalanceUseCase {

    private CashMachine cashMachine;

    private final LoadCashMachinePort loadCashMachinePort;

    @Override
    public BalanceEvent balance(BalanceCommand balanceCommand) {
        loadCashMachinePort.getById(1L).ifPresent(machine -> cashMachine = machine);
        return cashMachine.balance();
    }
}
