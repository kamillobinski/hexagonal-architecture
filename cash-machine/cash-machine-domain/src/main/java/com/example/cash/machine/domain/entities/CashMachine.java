package com.example.cash.machine.domain.entities;

import com.example.cash.machine.domain.commands.DepositCommand;
import com.example.cash.machine.domain.commands.WithdrawalCommand;
import com.example.cash.machine.domain.events.BalanceEvent;
import com.example.cash.machine.domain.events.BalanceEvent.EventType;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder(toBuilder = true)
public class CashMachine implements Serializable {

  private final BigDecimal balance;

  public BalanceEvent withdraw(WithdrawalCommand withdrawalCommand) {
    BigDecimal amount = withdrawalCommand.getAmount();
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    if (amount.compareTo(balance) > 0) {
      throw new IllegalArgumentException("Amount cannot be greater than the balance");
    }
    BigDecimal newBalance = balance.subtract(amount);
    return BalanceEvent.builder()
        .cashMachine(this.toBuilder().balance(newBalance).build())
        .balanceBefore(balance)
        .balanceAfter(newBalance)
        .eventType(EventType.WITHDRAWAL)
        .timestamp(Instant.now())
        .build();
  }

  public BalanceEvent deposit(DepositCommand depositCommand) {
    BigDecimal amount = depositCommand.getAmount();
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    BigDecimal newBalance = balance.add(amount);
    return BalanceEvent.builder()
        .cashMachine(this.toBuilder().balance(newBalance).build())
        .balanceBefore(balance)
        .balanceAfter(newBalance)
        .eventType(EventType.DEPOSIT)
        .timestamp(Instant.now())
        .build();
  }
}
