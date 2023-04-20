package com.example.cash.machine.domain.entities;

import com.example.cash.machine.domain.commands.DepositCommand;
import com.example.cash.machine.domain.commands.WithdrawalCommand;
import com.example.cash.machine.domain.enums.EventType;
import com.example.cash.machine.domain.events.BalanceEvent;
import com.example.cash.machine.domain.events.WithdrawalDepositEvent;
import com.example.cash.machine.domain.exception.CashMachineException;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder(toBuilder = true)
public class CashMachine implements Serializable {

    private final Long id;
    private final BigDecimal balance;

    public BalanceEvent balance() {
        return BalanceEvent.builder()
                .cashMachine(this)
                .eventType(EventType.BALANCE)
                .timestamp(Instant.now())
                .build();
    }

    public WithdrawalDepositEvent withdraw(WithdrawalCommand withdrawalCommand) {
        BigDecimal amount = withdrawalCommand.getAmount();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CashMachineException("Amount cannot be negative or zero");
        }
        if (amount.compareTo(balance) > 0) {
            throw new CashMachineException("Amount cannot be greater than the balance");
        }
        if (balance.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CashMachineException("Balance cannot be negative or zero");
        }
        BigDecimal newBalance = balance.subtract(amount);
        return WithdrawalDepositEvent.builder()
                .cashMachine(this.toBuilder().balance(newBalance).build())
                .balanceBefore(balance)
                .balanceAfter(newBalance)
                .eventType(EventType.WITHDRAWAL)
                .timestamp(Instant.now())
                .build();
    }

    public WithdrawalDepositEvent deposit(DepositCommand depositCommand) {
        BigDecimal amount = depositCommand.getAmount();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CashMachineException("Amount cannot be negative or zero");
        }
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new CashMachineException("Balance cannot be negative");
        }
        BigDecimal newBalance = balance.add(amount);
        return WithdrawalDepositEvent.builder()
                .cashMachine(this.toBuilder().balance(newBalance).build())
                .balanceBefore(balance)
                .balanceAfter(newBalance)
                .eventType(EventType.DEPOSIT)
                .timestamp(Instant.now())
                .build();
    }
}
