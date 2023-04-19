package com.example.cash.machine.adapter.in;

import com.example.cash.machine.adapter.out.exception.CashMachineErrorResponse;
import com.example.cash.machine.application.port.in.DepositUseCase;
import com.example.cash.machine.application.port.in.commands.DepositCommand;
import com.example.cash.machine.domain.exception.CashMachineException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class DepositController {

    private final DepositUseCase depositUseCase;

    @PostMapping("/deposit/{amount}")
    public ResponseEntity<Object> deposit(@PathVariable BigDecimal amount) {
        try {
            DepositCommand depositCommand = new DepositCommand(amount);
            return ResponseEntity.ok(depositUseCase.deposit(depositCommand));
        } catch (CashMachineException e) {
            return ResponseEntity.badRequest().body(
                    new CashMachineErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage())
            );
        }
    }
}
