package com.example.cash.machine.domain.entities;

import com.example.cash.machine.domain.commands.DepositCommand;
import com.example.cash.machine.domain.commands.WithdrawalCommand;
import com.example.cash.machine.domain.events.BalanceEvent;
import com.example.cash.machine.domain.events.BalanceEvent.EventType;
import java.io.Serializable;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class CashMachine implements Serializable {

  private final int balance;

  public BalanceEvent withdraw(WithdrawalCommand withdrawalCommand) {
    int amount = withdrawalCommand.getAmount();
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    if (amount > balance) {
      throw new IllegalArgumentException("Amount cannot be greater than the balance");
    }
    int newBalance = balance - amount;
    return BalanceEvent.builder()
        .cashMachine(this.toBuilder().balance(newBalance).build())
        .balanceBefore(balance)
        .balanceAfter(newBalance)
        .eventType(EventType.WITHDRAWAL)
        .timestamp(Instant.now())
        .build();
  }

  public BalanceEvent deposit(DepositCommand depositCommand) {
    int amount = depositCommand.getAmount();
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    int newBalance = balance + amount;
    return BalanceEvent.builder()
        .cashMachine(this.toBuilder().balance(newBalance).build())
        .balanceBefore(balance)
        .balanceAfter(newBalance)
        .eventType(EventType.DEPOSIT)
        .timestamp(Instant.now())
        .build();
  }
}
