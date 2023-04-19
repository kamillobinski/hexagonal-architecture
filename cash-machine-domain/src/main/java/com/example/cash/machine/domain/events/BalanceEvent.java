package com.example.cash.machine.domain.events;

import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.enums.EventType;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
@RequiredArgsConstructor(staticName = "of")
public class BalanceEvent {

    CashMachine cashMachine;
    EventType eventType;
    Instant timestamp;
}

