package com.example.cash.machine.adapter.in;

import com.example.cash.machine.application.port.in.DepositUseCase;
import com.example.cash.machine.application.port.in.commands.DepositCommand;
import com.example.cash.machine.domain.events.BalanceEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@Log
public class DepositController {

    private final DepositUseCase depositUseCase;

    @GetMapping("/ping")
    public String ping() {
        return "SERVER OK";
    }

    @GetMapping("/deposit/{amount}")
    public BalanceEvent deposit(@PathVariable BigDecimal amount) {
        DepositCommand depositCommand = new DepositCommand(amount);
        return depositUseCase.deposit(depositCommand);
    }
}
