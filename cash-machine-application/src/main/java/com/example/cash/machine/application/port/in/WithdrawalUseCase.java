package com.example.cash.machine.application.port.in;

import com.example.cash.machine.application.port.in.commands.WithdrawalCommand;
import com.example.cash.machine.domain.events.BalanceEvent;

public interface WithdrawalUseCase {

    BalanceEvent withdrawal(WithdrawalCommand withdrawalCommand);
}
