package com.example.cash.machine.domain.events;

import com.example.cash.machine.domain.entities.CashMachine;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
@Builder
@RequiredArgsConstructor(staticName = "of")
public class BalanceEvent {

  CashMachine cashMachine;
  BigDecimal balanceBefore;
  BigDecimal balanceAfter;
  EventType eventType;
  Instant timestamp;

  public enum EventType {
    WITHDRAWAL,
    DEPOSIT
  }
}
