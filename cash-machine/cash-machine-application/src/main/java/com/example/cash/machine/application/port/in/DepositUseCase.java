package com.example.cash.machine.application.port.in;

import com.example.cash.machine.application.port.in.commands.DepositCommand;
import com.example.cash.machine.domain.events.BalanceEvent;

public interface DepositUseCase {
    BalanceEvent deposit(DepositCommand depositCommand);
}
