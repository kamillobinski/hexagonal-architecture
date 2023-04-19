package com.example.cash.machine.adapter.in;

import com.example.cash.machine.adapter.out.exception.CashMachineErrorResponse;
import com.example.cash.machine.application.port.in.WithdrawalUseCase;
import com.example.cash.machine.application.port.in.commands.WithdrawalCommand;
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
public class WithdrawalController {

    private final WithdrawalUseCase withdrawalUseCase;

    @PostMapping("/withdraw/{amount}")
    public ResponseEntity<Object> withdraw(@PathVariable BigDecimal amount) {
        try {
            WithdrawalCommand withdrawalCommand = new WithdrawalCommand(amount);
            return ResponseEntity.ok(withdrawalUseCase.withdrawal(withdrawalCommand));
        } catch (CashMachineException e) {
            return ResponseEntity.badRequest().body(
                    new CashMachineErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage())
            );
        }
    }
}
