package com.example.cash.machine.domain.events;

import com.example.cash.machine.domain.entities.CashMachine;
import java.time.Instant;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor(staticName = "of")
public class BalanceEvent {

  final CashMachine cashMachine;
  final int balanceBefore;
  final int balanceAfter;
  final EventType eventType;
  final Instant timestamp;

  public enum EventType {
    WITHDRAWAL,
    DEPOSIT
  }
}
