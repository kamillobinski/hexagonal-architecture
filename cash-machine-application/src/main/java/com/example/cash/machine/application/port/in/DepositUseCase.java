package com.example.cash.machine.application.port.in;

import com.example.cash.machine.application.port.in.commands.DepositCommand;
import com.example.cash.machine.domain.events.WithdrawalDepositEvent;

public interface DepositUseCase {
    WithdrawalDepositEvent deposit(DepositCommand depositCommand);
}
