package com.example.cash.machine.application.service;

import com.example.cash.machine.application.port.in.DepositUseCase;
import com.example.cash.machine.application.port.in.commands.DepositCommand;
import com.example.cash.machine.application.port.out.LoadCashMachinePort;
import com.example.cash.machine.application.port.out.SaveCashMachinePort;
import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.events.WithdrawalDepositEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DepositService implements DepositUseCase {

    private CashMachine cashMachine;

    private final LoadCashMachinePort loadCashMachinePort;
    private final SaveCashMachinePort saveCashMachinePort;

    @Override
    public WithdrawalDepositEvent deposit(DepositCommand depositCommand) {
        loadCashMachinePort.getById(1L).ifPresent(machine -> cashMachine = machine);

        WithdrawalDepositEvent balanceEvent = cashMachine.deposit(
                com.example.cash.machine.domain.commands.DepositCommand
                        .builder().amount(depositCommand.amount()).build());

        saveCashMachinePort.save(balanceEvent.getCashMachine());

        return balanceEvent;
    }
}
