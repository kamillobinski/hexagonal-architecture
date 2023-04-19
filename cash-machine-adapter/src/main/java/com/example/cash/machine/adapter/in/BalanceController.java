package com.example.cash.machine.adapter.in;

import com.example.cash.machine.adapter.out.exception.CashMachineErrorResponse;
import com.example.cash.machine.application.port.in.BalanceUseCase;
import com.example.cash.machine.application.port.in.commands.BalanceCommand;
import com.example.cash.machine.domain.exception.CashMachineException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceUseCase balanceUseCase;

    @GetMapping("/balance")
    public ResponseEntity<Object> getBalance() {
        try {
            return ResponseEntity.ok(balanceUseCase.balance(new BalanceCommand()));
        } catch (CashMachineException e) {
            return ResponseEntity.badRequest().body(
                    new CashMachineErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage())
            );
        }
    }
}
