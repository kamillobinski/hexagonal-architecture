package com.example.cash.machine.application.port.in;

import com.example.cash.machine.application.port.in.commands.WithdrawalCommand;
import com.example.cash.machine.domain.events.WithdrawalDepositEvent;

public interface WithdrawalUseCase {

    WithdrawalDepositEvent withdrawal(WithdrawalCommand withdrawalCommand);
}
