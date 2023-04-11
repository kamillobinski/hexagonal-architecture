package com.example.cash.machine.application.service;

import com.example.cash.machine.application.port.in.commands.WithdrawalComand;
import com.example.cash.machine.application.port.in.WithdrawalUseCase;
import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.events.BalanceEvent;

public class WithdrawalService implements WithdrawalUseCase {

    private final CashMachine cashMachine;

    public WithdrawalService(CashMachine cashMachine) {
        this.cashMachine = cashMachine;
    }


    @Override
    public BalanceEvent withdrawal(WithdrawalComand withdrawalComand) {
        return cashMachine.withdraw(com.example.cash.machine.domain.commands.WithdrawalCommand
                .builder().amount(withdrawalComand.getAmount()).build());
    }
}
