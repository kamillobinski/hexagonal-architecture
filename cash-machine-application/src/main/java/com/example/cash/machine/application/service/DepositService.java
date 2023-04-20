package com.example.cash.machine.application.service;

import com.example.cash.machine.application.port.in.DepositUseCase;
import com.example.cash.machine.application.port.in.commands.DepositCommand;
import com.example.cash.machine.application.port.out.LoadCashMachinePort;
import com.example.cash.machine.application.port.out.SaveCashMachinePort;
import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.events.WithdrawalDepositEvent;
import com.example.cash.machine.domain.exception.CashMachineException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DepositService implements DepositUseCase {

    private final LoadCashMachinePort loadCashMachinePort;
    private final SaveCashMachinePort saveCashMachinePort;

    @Override
    public WithdrawalDepositEvent deposit(DepositCommand depositCommand) {
        Optional<CashMachine> cashMachineOptional = loadCashMachinePort.getById(1L);

        if (cashMachineOptional.isPresent()) {
            WithdrawalDepositEvent balanceEvent = cashMachineOptional.get().deposit(
                    com.example.cash.machine.domain.commands.DepositCommand
                            .builder().amount(depositCommand.amount()).build());
            saveCashMachinePort.save(balanceEvent.getCashMachine());

            return balanceEvent;
        } else {
            throw new CashMachineException("Cash machine not found");
        }
    }
}
