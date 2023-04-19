package com.example.cash.machine.adapter.in;

import com.example.cash.machine.application.port.in.WithdrawalUseCase;
import com.example.cash.machine.application.port.in.commands.WithdrawalCommand;
import com.example.cash.machine.domain.events.BalanceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class WithdrawalController {

    private final WithdrawalUseCase withdrawalUseCase;

    @PostMapping("/withdraw/{amount}")
    public BalanceEvent withdraw(@PathVariable BigDecimal amount) {
        WithdrawalCommand withdrawalCommand = new WithdrawalCommand(amount);
        return withdrawalUseCase.withdrawal(withdrawalCommand);
    }
}
