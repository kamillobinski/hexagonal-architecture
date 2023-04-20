package com.example.cash.machine.application.service;

import com.example.cash.machine.application.port.in.WithdrawalUseCase;
import com.example.cash.machine.application.port.in.commands.WithdrawalCommand;
import com.example.cash.machine.application.port.out.LoadCashMachinePort;
import com.example.cash.machine.application.port.out.SaveCashMachinePort;
import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.events.WithdrawalDepositEvent;
import com.example.cash.machine.domain.exception.CashMachineException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class WithdrawalService implements WithdrawalUseCase {

    private final LoadCashMachinePort loadCashMachinePort;
    private final SaveCashMachinePort saveCashMachinePort;

    @Override
    public WithdrawalDepositEvent withdrawal(WithdrawalCommand withdrawalCommand) {
        Optional<CashMachine> cashMachineOptional = loadCashMachinePort.getById(1L);

        if (cashMachineOptional.isPresent()) {
            WithdrawalDepositEvent balanceEvent = cashMachineOptional.get().withdraw(
                    com.example.cash.machine.domain.commands.WithdrawalCommand
                            .builder().amount(withdrawalCommand.amount()).build());
            saveCashMachinePort.save(balanceEvent.getCashMachine());

            return balanceEvent;
        } else {
            throw new CashMachineException("Cash machine not found");
        }

    }
}
