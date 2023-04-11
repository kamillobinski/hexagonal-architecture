package com.example.cash.machine.application.port.in.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Getter
@RequiredArgsConstructor
public class WithdrawalComand {

    BigDecimal amount;
}
