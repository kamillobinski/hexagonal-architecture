package com.example.cash.machine.application.port.in;

import com.example.cash.machine.application.port.in.commands.BalanceCommand;
import com.example.cash.machine.domain.events.BalanceEvent;

public interface BalanceUseCase {

    BalanceEvent balance(BalanceCommand balanceCommand);
}
