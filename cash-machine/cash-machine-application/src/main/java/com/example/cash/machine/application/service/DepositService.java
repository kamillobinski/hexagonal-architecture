package com.example.cash.machine.application.service;

import com.example.cash.machine.application.port.in.commands.DepositCommand;
import com.example.cash.machine.application.port.in.DepositUseCase;
import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.events.BalanceEvent;

public class DepositService implements DepositUseCase {

    private final CashMachine cashMachine;

    public DepositService(CashMachine cashMachine) {
        this.cashMachine = cashMachine;
    }

    @Override
    public BalanceEvent deposit(DepositCommand depositCommand) {
        return cashMachine.deposit(com.example.cash.machine.domain.commands.DepositCommand
                .builder().amount(depositCommand.getAmount()).build());
    }
}
