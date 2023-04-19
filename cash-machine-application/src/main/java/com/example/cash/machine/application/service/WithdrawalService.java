package com.example.cash.machine.application.service;

import com.example.cash.machine.application.port.in.WithdrawalUseCase;
import com.example.cash.machine.application.port.in.commands.WithdrawalCommand;
import com.example.cash.machine.application.port.out.LoadCashMachinePort;
import com.example.cash.machine.application.port.out.SaveCashMachinePort;
import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.events.WithdrawalDepositEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WithdrawalService implements WithdrawalUseCase {

    private CashMachine cashMachine;

    private final LoadCashMachinePort loadCashMachinePort;
    private final SaveCashMachinePort saveCashMachinePort;

    @Override
    public WithdrawalDepositEvent withdrawal(WithdrawalCommand withdrawalCommand) {
        loadCashMachinePort.getById(1L).ifPresent(machine -> cashMachine = machine);

        WithdrawalDepositEvent balanceEvent = cashMachine.withdraw(
                com.example.cash.machine.domain.commands.WithdrawalCommand
                        .builder().amount(withdrawalCommand.amount()).build());

        saveCashMachinePort.save(balanceEvent.getCashMachine());

        return balanceEvent;
    }
}
